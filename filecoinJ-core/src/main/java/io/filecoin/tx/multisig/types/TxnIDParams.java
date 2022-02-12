package io.filecoin.tx.multisig.types;

import com.google.common.collect.Lists;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.protocol.constants.Constants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TxnIDParams implements CBORMarshaler {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long txnID;
    private String proposalHash;

    public TxnIDParams() {
    }

    public Long getTxnID() {
        return txnID;
    }

    public void setTxnID(Long txnID) {
        this.txnID = txnID;
    }

    public String getProposalHash() {
        return proposalHash;
    }

    public void setProposalHash(String proposalHash) {
        this.proposalHash = proposalHash;
    }

    @Override
    public byte[] marshalCBOR() {
        byte[] cidHashBytes = new byte[]{};
        if(StringUtils.isNotBlank(proposalHash)){
            cidHashBytes = Base64.getDecoder().decode(proposalHash);
        }

        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborLong(txnID));

        //confirm the hashes match, if present.
        list.add(new CborObject.CborByteArray(cidHashBytes));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));

        byte[] constructorParams = cborList.toByteArray();
        constructorParams = ArrayUtils.subarray(constructorParams, 1, constructorParams.length);

        return constructorParams;
    }
}
