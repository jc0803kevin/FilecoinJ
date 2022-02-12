package io.filecoin.tx.multisig.types;

import com.google.common.collect.Lists;
import io.filecoin.crypto.Address;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.protocol.constants.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProposeParams implements CBORMarshaler, Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String to;
    private BigInteger value;
    private Integer method;
    private String params;

    @Override
    public byte[] marshalCBOR() {

        byte[] valueBuffer = Signer.unsigned(value);
        byte[] parsmByte = StringUtils.isBlank(params) ? new byte[]{} : Base64.decodeBase64(params);
        //序列化
        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborByteArray(Address.fromString(to).getPayload()));
        list.add(new CborObject.CborByteArray(valueBuffer));
//        list.add(new CborObject.CborLong(0L));
        list.add(new CborObject.CborLong(method));

        list.add(new CborObject.CborByteArray(parsmByte));
        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));
        byte[] proposeParams = cborList.toByteArray();

        proposeParams = ArrayUtils.subarray(proposeParams, 1, proposeParams.length);
        return proposeParams;
    }

    public ProposeParams() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
