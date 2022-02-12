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

public class AddSignerParams implements Serializable, CBORMarshaler {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String newSigner;
    private boolean increase;

    public AddSignerParams() {
    }

    @Override
    public byte[] marshalCBOR() {

        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborByteArray(Address.fromString(newSigner).getPayload()));
        list.add(new CborObject.CborBoolean(increase));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));
        byte[] enc = cborList.toByteArray();
        enc = ArrayUtils.subarray(enc, 1, enc.length);

        return enc;
    }

    public String getNewSigner() {
        return newSigner;
    }

    public void setNewSigner(String newSigner) {
        this.newSigner = newSigner;
    }

    public boolean isIncrease() {
        return increase;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }
}
