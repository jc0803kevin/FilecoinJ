package io.filecoin.tx.multisig;

import io.filecoin.crypto.NetworkParameters;
import io.filecoin.crypto.types.Message;
import io.filecoin.tx.multisig.types.CreateMultisigMsgParam;
import org.apache.commons.codec.binary.Base64;

import java.util.List;

public class MultiCreateMsgBuilder extends MultiMessageBuilder {

    private static final String MAIN_NET_TO = "f01";
    private static final String TEST_NET_TO = "t01";

    /**
    * @author kevin
    * @description creates a multisig wallet
    * @param parameters network
    * @param proposer sender address of the create msg
    * @param codeCID MultisigActorCodeID
    * @param signers approving addresses
    * @param requiredNumberOfApprovals required number of senders
    * @since 1.0.0
    */
    public static Message create(NetworkParameters parameters, String proposer, String codeCID, List<String> signers, Integer requiredNumberOfApprovals){

        Message message = new Message();
        message.setTo(NetworkParameters.isMainNet(parameters) ? MAIN_NET_TO : TEST_NET_TO);       //to地址固定f01
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(2L);      //方法编号2
        message.setValue("0");

        CreateMultisigMsgParam param = new CreateMultisigMsgParam();
        param.setCodeCID(codeCID);
        param.setRequiredNumberOfApprovals(requiredNumberOfApprovals);
        param.setSigners(signers);
        param.setStartEpoch(0);
        param.setUnlockDuration(0);
        String params = Base64.encodeBase64String(param.marshalCBOR());
        message.setParams(params);

        return message;
    }

}
