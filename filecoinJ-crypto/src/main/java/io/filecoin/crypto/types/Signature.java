package io.filecoin.crypto.types;

import io.filecoin.crypto.CBORMarshaler;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;

public class Signature implements Serializable, CBORMarshaler {
    private static final long serialVersionUID = 1L;

    private Integer type;
    private String data;

    public Signature() {
    }

    public Signature(Integer type, String data) {
        this.type = type;
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Only Secp256k1A signature conversion is supported
     * 0x58 -> 88 encoding prefix
     * 0x42 -> 66 tag
     * 0x01 -> 01
     * @return
     */
    @Override
    public byte[] marshalCBOR() {
        byte[] prefix = ArrayUtils.addAll(new byte[]{88}, new byte[]{66});
        byte[] type = ArrayUtils.addAll(prefix, new byte[]{01});
        return ArrayUtils.addAll(type, Base64.decodeBase64(data));
    }
}
