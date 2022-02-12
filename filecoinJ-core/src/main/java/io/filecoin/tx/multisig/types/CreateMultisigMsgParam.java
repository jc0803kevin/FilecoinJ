package io.filecoin.tx.multisig.types;

import com.google.common.collect.Lists;
import io.filecoin.crypto.Address;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.NetworkParameters;
import io.filecoin.crypto.UnmarshalCBOR;
import io.filecoin.crypto.cbor.CborEncoder;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.crypto.cbor.Cborable;
import io.filecoin.crypto.exception.CBORException;
import io.filecoin.protocol.constants.Constants;
import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author kevin
 * @description Build multi-signature address parameters
 * @since 1.0.0
 */
public class CreateMultisigMsgParam implements CBORMarshaler, UnmarshalCBOR<CreateMultisigMsgParam> {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private List<String> signers;            //approving addresses
    private int requiredNumberOfApprovals;    //required number of senders
    private int unlockDuration;                //unlock duration，
    private int startEpoch;                    //
    private String codeCID;                    //
    private NetworkParameters networkParameters;

    @Override
    public byte[] marshalCBOR() {
        List<CborObject.CborByteArray> addressList = Lists.newArrayList();
        for (String address : signers) {
            addressList.add(new CborObject.CborByteArray(Address.fromString(address).getPayload()));
        }
        //序列化
        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborList(addressList));
        list.add(new CborObject.CborLong(requiredNumberOfApprovals));
        list.add(new CborObject.CborLong(unlockDuration));
        list.add(new CborObject.CborLong(startEpoch));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));

        byte[] constructorParams = cborList.toByteArray();

        constructorParams = ArrayUtils.subarray(constructorParams, 1, constructorParams.length);

        byte[] cidHashBytes = createCid(codeCID.getBytes());

        list = new ArrayList<>();
        list.add(new CborCid(cidHashBytes));
        list.add(new CborObject.CborByteArray(constructorParams));
        cborList = new CborObject.CborList(list);

        return cborList.toByteArray();
    }

    @Override
    public CreateMultisigMsgParam unmarshalCBOR(byte[] payload) {

        CborObject.CborList cborList = (CborObject.CborList) CborObject.fromByteArray(payload);
        List<? extends Cborable> value = cborList.value;
        if (value.size() != 2) {
            throw new CBORException("Exec Params Size != 2");
        }
        CborObject.CborByteArray constructorParams = (CborObject.CborByteArray) value.get(1);

        cborList = (CborObject.CborList) CborObject.fromByteArray(constructorParams.value);
        value = cborList.value;
        if (value.size() != 4) {
            throw new CBORException("constructor Params Size != 4");
        }

        CborObject.CborList signList = (CborObject.CborList) value.get(0);
        CborObject.CborLong requiredNumberOfApprovals = (CborObject.CborLong) value.get(1);
        CborObject.CborLong unlockDuration = (CborObject.CborLong) value.get(2);
        CborObject.CborLong startEpoch = (CborObject.CborLong) value.get(3);
        this.requiredNumberOfApprovals = Long.valueOf(requiredNumberOfApprovals.value).intValue();
        this.unlockDuration = Long.valueOf(unlockDuration.value).intValue();
        this.startEpoch = Long.valueOf(startEpoch.value).intValue();

        List<String> addresses = new ArrayList<>();
        for (Cborable cborable : signList.value) {
            byte[] addressByte = cborable.toCbor().toByteArray();

            addressByte = ArrayUtils.subarray(addressByte, 1, addressByte.length);
            addresses.add(Address.fromByte(networkParameters == null ? NetworkParameters.MAIN_NET : networkParameters, addressByte).toString());
        }
        this.signers = addresses;
        return this;
    }

    public class CborCid implements CborObject {
        final byte[] value;

        public CborCid(byte[] value) {
            this.value = value;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeTag(LINK_TAG);
                byte[] withMultibaseHeader = new byte[value.length + 1];
                System.arraycopy(value, 0, withMultibaseHeader, 1, value.length);
                encoder.writeByteString(withMultibaseHeader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return null;
        }

    }

    public byte[] createCid(final byte[] data) {
        final Cid cid = Cid.buildCidV1(Cid.Codec.Raw, Multihash.Type.id, data);
        return cid.toBytes();
    }

    public CreateMultisigMsgParam() {
    }

    public List<String> getSigners() {
        return signers;
    }

    public void setSigners(List<String> signers) {
        this.signers = signers;
    }

    public int getRequiredNumberOfApprovals() {
        return requiredNumberOfApprovals;
    }

    public void setRequiredNumberOfApprovals(int requiredNumberOfApprovals) {
        this.requiredNumberOfApprovals = requiredNumberOfApprovals;
    }

    public int getUnlockDuration() {
        return unlockDuration;
    }

    public void setUnlockDuration(int unlockDuration) {
        this.unlockDuration = unlockDuration;
    }

    public int getStartEpoch() {
        return startEpoch;
    }

    public void setStartEpoch(int startEpoch) {
        this.startEpoch = startEpoch;
    }

    public String getCodeCID() {
        return codeCID;
    }

    public void setCodeCID(String codeCID) {
        this.codeCID = codeCID;
    }

    public NetworkParameters getNetworkParameters() {
        return networkParameters;
    }

    public void setNetworkParameters(NetworkParameters networkParameters) {
        this.networkParameters = networkParameters;
    }
}
