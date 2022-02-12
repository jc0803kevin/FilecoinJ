package io.filecoin.tx.multisig;

import io.filecoin.crypto.Signer;
import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.crypto.types.Message;
import io.filecoin.tx.multisig.types.ProposalHashData;
import io.filecoin.tx.multisig.types.TxnIDParams;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;

public class MultiCancelMsgBuilder extends MultiMessageBuilder {

    /**
    * @author kevin
    * @description cancels a previously-proposed multisig message
    * @param multiAddress multisig address
    * @param signer sender address of the cancel msg
    * @param proposerId proposed transaction ID
    */
    public static Message cancelTx(String multiAddress, String signer, Long proposerId){
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer); // only proposer do cancel
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());
        message.setValue("0");

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(new byte[]{}));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @author kevin
     * @description cancels a previously-proposed multisig message AND  confirm the hashes match
     * @param multiAddress multisig address
     * @param signer sender address of the cancel msg
     * @param proposerIDAddress proposed transaction ID
     * @param proposerId proposed transaction ID
     * @param toAddress recipient address
     * @param toAmount value to transfer
     */
    public static Message cancelTxWithHash(String multiAddress, String signer, String proposerIDAddress, Long proposerId, String toAddress, BigInteger toAmount){
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer); // only proposer do cancel
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());
        message.setValue("0");

        ProposalHashData proposalHashData = ProposalHashData.msigApproveOrCancelTxnHash(multiAddress, proposerId, proposerIDAddress,toAddress, toAmount,signer, 0L, new byte[]{});
        byte[] hash =  Signer.createHash(proposalHashData.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @author kevin
     * @description cancels a previously proposed AddSigner message
     * @param multiAddress multisig address
     * @param signer sender address of the cancel msg
     * @param proposerId proposed transaction ID
     */
    public static Message msigAddCancel(String multiAddress,String signer,Long proposerId){

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());      //方法编号3
        message.setValue("0");

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(new byte[]{}));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @author kevin
     * @description cancels a previously proposed AddSigner message and confirm the hashes match
     * @param multiAddress multisig address
     * @param signer sender address of the cancel msg
     * @param proposerIDAddress proposed transaction ID
     * @param proposerId proposed transaction ID
     * @param newAdd new signer
     * @param inc whether the number of required signers should be increased
     */
    public static Message msigAddCancelWithHash(String multiAddress,String signer,String proposerIDAddress, Long proposerId, String newAdd, boolean inc){

        ProposalHashData proposalHashData = ProposalHashData.msigAddCancel(multiAddress, proposerIDAddress, signer, proposerId, newAdd, inc);

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());      //方法编号3
        message.setValue("0");

        byte[] hash =  Signer.createHash(proposalHashData.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @author kevin
     * @description cancels a previously proposed SwapSigner message
     * @param multiAddress multisig address
     * @param signer sender address of the approve msg
     * @param proposerId proposed message ID
     * @param proposerIDAddress proposer address
     * @param oldAdd old signer
     * @param newAdd new signer
     */
    public static Message msigSwapCancel(String multiAddress, String signer,Long proposerId,String proposerIDAddress, String oldAdd, String newAdd){

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(signer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());      //方法编号3
        message.setValue("0");

        ProposalHashData proposalHashData = ProposalHashData.msigSwapCancel(multiAddress,signer, proposerId,proposerIDAddress, oldAdd, newAdd);

        byte[] hash =  Signer.createHash(proposalHashData.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposerId);
        txnIDParams.setProposalHash(Base64.encodeBase64String(hash));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);

        return message;
    }
}
