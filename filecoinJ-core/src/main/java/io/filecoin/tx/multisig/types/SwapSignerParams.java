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

public class SwapSignerParams implements Serializable, CBORMarshaler {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String from;
    private String to;

    public SwapSignerParams() {
    }

    public SwapSignerParams(String from, String to) {
        this.from = from;
        this.to = to;
    }


    @Override
    public byte[] marshalCBOR() {
        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborByteArray(Address.fromString(from).getPayload()));
        list.add(new CborObject.CborByteArray(Address.fromString(to).getPayload()));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));
        byte[] enc = cborList.toByteArray();
        enc = ArrayUtils.subarray(enc, 1, enc.length);

        return enc;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
