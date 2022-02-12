package io.filecoin.tx.multisig.types;

import com.google.common.collect.Lists;
import io.filecoin.crypto.Address;
import io.filecoin.crypto.CBORMarshaler;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.protocol.constants.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProposalHashData implements Serializable, CBORMarshaler {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long txID;
    private String requester;       //The requesting multisig wallet member.
    private String to;
    private BigInteger value;
    private Long method;
    private String params;

    public static ProposalHashData msigAddCancel(String multiAddress,String proposerIDAddress, String src, Long txID, String newAdd, boolean inc){
        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner(newAdd);
        addSignerParams.setIncrease(inc);

        return msigCancel(multiAddress,proposerIDAddress, txID, multiAddress, BigInteger.ZERO, src, 0L, addSignerParams.marshalCBOR());
    }

    public static ProposalHashData msigCancel(String multiAddress,String proposerIDAddress, Long txID, String to, BigInteger amount, String src,Long method, byte[] params){
        return msigApproveOrCancelTxnHash(multiAddress, txID, proposerIDAddress, to, amount, src, method, params);
    }

    public static ProposalHashData msigApproveOrCancelTxnHash(String multiAddress, Long txID, String proposerIDAddress, String to, BigInteger amount, String src, Long method, byte[] params) {

        ProposalHashData param = new ProposalHashData();
        param.setTo(to);
        param.setValue(amount);
        param.setRequester(proposerIDAddress);
        param.setTxID(txID);
        param.setMethod(method);
        param.setParams(Base64.encodeBase64String(params));

        return param;
    }


    public static ProposalHashData msigAddApprove(String multiAddress,String sign, Long txID, String proposer, String newAdd, boolean inc) {

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner(newAdd);
        addSignerParams.setIncrease(inc);

        return msigApproveTxnHash(multiAddress, txID, proposer, multiAddress, BigInteger.ZERO, sign, MethodsMultisig.AddSigner.getMethod(), addSignerParams.marshalCBOR());
    }

    public static ProposalHashData msigRemoveApprove(String multiAddress,String sign, Long txID, String proposer, String newAdd, boolean inc) {

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner(newAdd);
        addSignerParams.setIncrease(inc);

        return msigApproveTxnHash(multiAddress, txID, proposer, multiAddress, BigInteger.ZERO, sign, MethodsMultisig.RemoveSigner.getMethod(), addSignerParams.marshalCBOR());
    }

    public static ProposalHashData msigSwapApprove(String multiAddress, String sign, Long txID, String proposer, String oldAdd, String newAdd){

        SwapSignerParams swapSignerParams = new SwapSignerParams();
        swapSignerParams.setFrom(oldAdd);
        swapSignerParams.setTo(newAdd);

        return msigApproveTxnHash(multiAddress, txID, proposer, multiAddress, BigInteger.ZERO, sign, MethodsMultisig.SwapSigner.getMethod(), swapSignerParams.marshalCBOR());
    }

    public static ProposalHashData msigSwapCancel(String multiAddress, String sign, Long txID, String proposer, String oldAdd, String newAdd){

        SwapSignerParams swapSignerParams = new SwapSignerParams();
        swapSignerParams.setFrom(oldAdd);
        swapSignerParams.setTo(newAdd);

        return msigApproveTxnHash(multiAddress, txID, proposer, multiAddress, BigInteger.ZERO, sign, MethodsMultisig.SwapSigner.getMethod(), swapSignerParams.marshalCBOR());
    }

    public static ProposalHashData msigApproveTxnHash(String multiAddress,Long txID, String proposer, String to, BigInteger amount,
                                      String src, Long method, byte[] params){

        return msigApproveOrCancelTxnHash(multiAddress, txID, proposer, to, amount, src, method, params);
    }

    @Override
    public byte[] marshalCBOR() {

        byte[] paramByte = StringUtils.isBlank(params) ? new byte[]{} : Base64.decodeBase64(params);

        List<CborObject> list = new ArrayList<>();
        list.add(new CborObject.CborByteArray(Address.fromString(requester).getPayload()));
        list.add(new CborObject.CborByteArray(Address.fromString(to).getPayload()));
        list.add(new CborObject.CborByteArray(Signer.unsigned(value)));
//        list.add(new CborObject.CborLong(0L));
        list.add(new CborObject.CborLong(method));
        list.add(new CborObject.CborByteArray(paramByte));

        CborObject.CborList cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));

        byte[] serializedProposalParams = cborList.toByteArray();
        serializedProposalParams = ArrayUtils.subarray(serializedProposalParams, 1, serializedProposalParams.length);
        return serializedProposalParams;

//        //参数hash值
//        byte[] hash = FilecoinSign.createHash(serializedProposalParams);
//
//        list = new ArrayList<>();
//        list.add(new CborObject.CborLong(txID));
//        list.add(new CborObject.CborByteArray(hash));
//        cborList = new CborObject.CborList(Lists.newArrayList(new CborObject.CborList(list)));
//
//        byte[] params = cborList.toByteArray();
//        params = ArrayUtils.subarray(params, 1, params.length);

//        return params;
    }

    public ProposalHashData() {
    }

    public Long getTxID() {
        return txID;
    }

    public void setTxID(Long txID) {
        this.txID = txID;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
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
}
