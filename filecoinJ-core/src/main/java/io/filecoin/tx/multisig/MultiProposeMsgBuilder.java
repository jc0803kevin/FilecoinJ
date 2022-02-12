package io.filecoin.tx.multisig;

import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.crypto.types.Message;
import io.filecoin.tx.multisig.types.AddSignerParams;
import io.filecoin.tx.multisig.types.ProposeParams;
import io.filecoin.tx.multisig.types.SwapSignerParams;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;

public class MultiProposeMsgBuilder extends MultiMessageBuilder {

    /**
     * @description  proposes a multisig message
     * @param multiAddress Multi-signature address
     * @param proposer sender address of the propose msg
     * @param toAddress receiving address
     * @param toAmount received amount
     */
    public static Message proposeTx(String multiAddress, String proposer, String toAddress, BigInteger toAmount) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(2L);      //方法编号2
        message.setValue("0");

        ProposeParams param = new ProposeParams();
        param.setTo(toAddress);
        param.setValue(toAmount);
        param.setMethod(0);

        String params = Base64.encodeBase64String(param.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @description  proposes adding a signer in the multisig
     * @param multiAddress Multi-signature address
     * @param proposer sender address of the propose msg
     * @param newAdd new signer
     * @param inc whether the number of required signers should be increased
     */
    public static Message msigAddPropose(String multiAddress, String proposer, String newAdd, boolean inc) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Propose.getMethod());
        message.setValue("0");

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner(newAdd);
        addSignerParams.setIncrease(inc);

        ProposeParams proposeParams = new ProposeParams();
        proposeParams.setTo(multiAddress);
        proposeParams.setMethod(Long.valueOf(MethodsMultisig.AddSigner.getMethod()).intValue());
        proposeParams.setParams(Base64.encodeBase64String(addSignerParams.marshalCBOR()));
        proposeParams.setValue(new BigInteger("0"));

        String params = Base64.encodeBase64String(proposeParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
     * @description  proposes the removal of a signer from the multisig.
     * @param multiAddress Multi-signature address
     * @param proposer sender address of the propose msg
     * @param toRemove the address to be removed
     * @param decrease indicating whether or not the signing threshold should be lowered by one along with the address removal.
     */
    public static Message msigRemoveSigner(String multiAddress, String proposer, String toRemove, boolean decrease) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Propose.getMethod());
        message.setValue("0");

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner(toRemove);
        addSignerParams.setIncrease(decrease);

        ProposeParams proposeParams = new ProposeParams();
        proposeParams.setTo(multiAddress);
        proposeParams.setMethod(Long.valueOf(MethodsMultisig.RemoveSigner.getMethod()).intValue());
        proposeParams.setParams(Base64.encodeBase64String(addSignerParams.marshalCBOR()));
        proposeParams.setValue(new BigInteger("0"));

        String params = Base64.encodeBase64String(proposeParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

    /**
    * @author kevin
    * @description proposes swapping 2 signers in the multisig
    * @param multiAddress  Multi-signature address
    * @param proposer sender address of the propose msg
    * @param oldAdd old signer
    * @param newAdd new signer
    */
    public static Message msigSwapPropose(String multiAddress, String proposer, String oldAdd, String newAdd) {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Propose.getMethod());
        message.setValue("0");

        SwapSignerParams swapSignerParams = new SwapSignerParams();
        swapSignerParams.setFrom(oldAdd);
        swapSignerParams.setTo(newAdd);

        ProposeParams proposeParams = new ProposeParams();
        proposeParams.setTo(multiAddress);
        proposeParams.setMethod(Long.valueOf(MethodsMultisig.SwapSigner.getMethod()).intValue());
        proposeParams.setParams(Base64.encodeBase64String(swapSignerParams.marshalCBOR()));
        proposeParams.setValue(new BigInteger("0"));

        String params = Base64.encodeBase64String(proposeParams.marshalCBOR());
        message.setParams(params);

        return message;
    }

}
