package io.filecoin.crypto.types;

import io.filecoin.crypto.Address;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.cbor.CborObject;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable, CBORMarshaler {
    private static final long serialVersionUID = 1L;

    private Long version;
    private String to;
    private String from;
    private Long nonce;
    private String value;
    private Long gasLimit;
    private String gasFeeCap;
    private String gasPremium;
    private Long method;
    private String params;

    private Cid cid;


    public Message() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(Long gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getGasFeeCap() {
        return gasFeeCap;
    }

    public void setGasFeeCap(String gasFeeCap) {
        this.gasFeeCap = gasFeeCap;
    }

    public String getGasPremium() {
        return gasPremium;
    }

    public void setGasPremium(String gasPremium) {
        this.gasPremium = gasPremium;
    }

    public Long getMethod() {
        return method;
    }

    public void setMethod(Long method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }

    @Override
    public byte[] marshalCBOR() {
        //交易体序列化为cbor格式
        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborLong(this.getVersion()));
        list.add(new CborObject.CborByteArray(Address.fromString(this.getTo()).getPayload()));
        list.add(new CborObject.CborByteArray(Address.fromString(this.getFrom()).getPayload()));
        list.add(new CborObject.CborLong(this.getNonce()));
        list.add(new CborObject.CborByteArray(Signer.unsigned(new BigInteger(this.getValue()))));
        list.add(new CborObject.CborLong(this.getGasLimit()));
        list.add(new CborObject.CborByteArray(Signer.unsigned(new BigInteger(this.getGasFeeCap()))));
        list.add(new CborObject.CborByteArray(Signer.unsigned(new BigInteger(this.getGasPremium()))));
        list.add(new CborObject.CborLong(this.getMethod()));
        list.add(new CborObject.CborByteArray(Base64.decodeBase64(this.getParams())));

        CborObject.CborList cborList = new CborObject.CborList(list);
        byte[] encodedBytes = cborList.toByteArray();

        return encodedBytes;
    }
}
