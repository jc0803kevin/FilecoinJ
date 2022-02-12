package io.filecoin.tx.multisig;

import io.filecoin.crypto.Signer;
import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.crypto.types.Message;
import io.filecoin.tx.multisig.types.ProposalHashData;
import io.filecoin.tx.multisig.types.TxnIDParams;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;

public class MultiApproveMsgBuilder extends MultiMessageBuilder {

    /**
    * @description Approve the transaction
    * @param multiAddress Multi-signature address
    * @param signer Approve transaction signers
    * @param proposerIDAddress proposer id address
    * @param proposerId message id
    * @param toAddress receiving address
    * @param toAmount received amount
    */
    public static Message approveTx(String multiAddress, String signer, String proposerIDAddress, Long proposerId, String toAddress, BigInteger toAmount){
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());      //方法编号3
        message.setValue("0");

        ProposalHashData param = new ProposalHashData();
        param.setTo(toAddress);
        param.setValue(toAmount);
        param.setRequester(proposerIDAddress);
        param.setTxID(proposerId);
        param.setMethod(0L);
        param.setParams(Base64.encodeBase64String(new byte[]{}));


        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(new byte[]{}));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @description Approve the transaction
     * @param multiAddress Multi-signature address
     * @param signer Approve transaction signers
     * @param proposerIDAddress proposer id address
     * @param proposerId message id
     * @param toAddress receiving address
     * @param toAmount received amount
     */
    public static Message approveTxWithHash(String multiAddress, String signer, String proposerIDAddress, Long proposerId, String toAddress, BigInteger toAmount){
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());      //方法编号3
        message.setValue("0");

        ProposalHashData param = new ProposalHashData();
        param.setTo(toAddress);
        param.setValue(toAmount);
        param.setRequester(proposerIDAddress);
        param.setTxID(proposerId);
        param.setMethod(0L);
        param.setParams(Base64.encodeBase64String(new byte[]{}));

        byte[] hash =  Signer.createHash(param.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
    * @author kevin
    * @description approves a previously proposed AddSigner message
    * @param multiAddress multisig address
    * @param signer sender address of the approve msg
    * @param proposerId proposed message ID
    * @param proposerIDAddress proposer address
    * @param newAdd new signer
    * @param inc whether the number of required signers should be increased
    */
    public static Message msigAddApprove(String multiAddress, String signer,Long proposerId, String proposerIDAddress,  String newAdd, boolean inc) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());
        message.setValue("0");

        ProposalHashData param = ProposalHashData.msigAddApprove(multiAddress, signer, proposerId, proposerIDAddress, newAdd, inc);

        byte[] hash =  Signer.createHash(param.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @author kevin
     * @description approves the removal of a signer from the multisig
     * @param multiAddress multisig address
     * @param signer sender address of the approve msg
     * @param proposerId proposed message ID
     * @param proposerIDAddress proposer address
     * @param newAdd new signer
     * @param inc whether the number of required signers should be increased
     */
    public static Message msigRemoveApprove(String multiAddress, String signer,Long proposerId, String proposerIDAddress,  String newAdd, boolean inc) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());
        message.setValue("0");

        ProposalHashData param = ProposalHashData.msigRemoveApprove(multiAddress, signer, proposerId, proposerIDAddress, newAdd, inc);

        byte[] hash =  Signer.createHash(param.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
    * @author kevin
    * @description swapping 2 signers in the multisig
    * @param multiAddress multisig address
    * @param signer sender address of the approve msg
    * @param proposerId proposed message ID
    * @param proposerIDAddress proposer address
    * @param oldAdd old signer
    * @param newAdd new signer
    */
    public static Message msigSwapApprove(String multiAddress, String signer,Long proposerId, String proposerIDAddress,  String oldAdd, String newAdd) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());
        message.setValue("0");

        ProposalHashData param = ProposalHashData.msigSwapApprove(multiAddress, signer, proposerId, proposerIDAddress, oldAdd, newAdd);

        byte[] hash =  Signer.createHash(param.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

}
