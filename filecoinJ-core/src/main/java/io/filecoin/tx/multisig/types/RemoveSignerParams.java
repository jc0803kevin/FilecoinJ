package io.filecoin.tx.multisig.types;

import com.google.common.collect.Lists;
import io.filecoin.crypto.Address;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.protocol.constants.Constants;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RemoveSignerParams implements Serializable , CBORMarshaler {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String signer;
    private boolean decrease;

    public RemoveSignerParams() {
    }

    @Override
    public byte[] marshalCBOR() {

        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborByteArray(Address.fromString(signer).getPayload()));
        list.add(new CborObject.CborBoolean(decrease));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));
        byte[] enc = cborList.toByteArray();
        enc = ArrayUtils.subarray(enc, 1, enc.length);

        return enc;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public boolean isDecrease() {
        return decrease;
    }

    public void setDecrease(boolean decrease) {
        this.decrease = decrease;
    }
}
