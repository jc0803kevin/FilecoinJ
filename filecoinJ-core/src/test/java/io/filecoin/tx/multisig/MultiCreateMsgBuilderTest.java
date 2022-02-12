package io.filecoin.tx.multisig;

import com.alibaba.fastjson.JSON;
import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.NetworkParameters;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiCreateMsgBuilderTest extends BaseTest {

    private static List<String> signers = new ArrayList<>();
    private static String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";

    static {
        signers.add("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        signers.add("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        signers.add("t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq");
    }

    @Test
    public void build() throws IOException {

        Message message = MultiCreateMsgBuilder.create(NetworkParameters.TEST_NET, proposer, "fil/6/multisig",signers, 2);

        message.setNonce(72L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("gtgqUwABVQAOZmlsLzYvbXVsdGlzaWdYR4SDVQGRch3xRApUgOOPqWSsqVBnM9jtq1UB6j7tercApPvp9glDIhCLyD/gbpZVAfQLkKrUzhhOCktTu9oQdwoC/rPRAgAA",message.getParams());
        Assert.assertEquals("AajSB8RoAWcOhXBZFBj45DnmUoj+kPIbhfTLPjIl0OcIZCs5vvadyJR3HOID1IKDFZPBycMMa+nFM97Z62STYwE=",signedMessage.getSignature().getData());

    }

}
