package io.filecoin.tx.multisig;

import com.alibaba.fastjson.JSON;
import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.domain.types.TipSetKey;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class MultiApproveMsgBuilderTest extends BaseTest {
    private static String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
    private static String multiAddress = "t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q";
    private static String to = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";
    private static BigInteger toAmount = new BigInteger("100");

    @Test
    public void approveTx() throws IOException {
        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));

        String proposerIDAddres = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiApproveMsgBuilder.approveTx(multiAddress, signer, proposerIDAddres, 10L, to, toAmount);

        message.setNonce(14L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");


        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

//        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
//        System.out.println("RawResponse-->" + result.getRawResponse());

        Assert.assertEquals("ggpA",message.getParams());
        Assert.assertEquals("4GSdksyLxbhMcQFbnlds6Z5F7kR7xmpcEkVjCkA+YYAw/fYhpuRWl9Vj3uCmQ5loKdI7pQPEsgiPrr3U+bqH8gE=",signedMessage.getSignature().getData());

    }

    @Test
    public void approveTxWithHash() throws IOException {

        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));


        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiApproveMsgBuilder.approveTxWithHash(multiAddress, signer, proposerIDAddress, 9L, to, toAmount);

        message.setNonce(13L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");


        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("gglYILithh5L9VdqHyfxVOcPDxkx0oIEJ8lOjGlZ4hfzAe7m",message.getParams());
        Assert.assertEquals("TC5Iu0vS9DBCNWz70VgrLQpfou9/BvVf9MpqqVRThUIkyRYq/71n/+VRVsIaQ9nLd9j3cTxz8V4UOMchU4zytQE=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigAddApprove() throws IOException {

        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));

        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiApproveMsgBuilder.msigAddApprove(multiAddress, signer, 12L, proposerIDAddress,"t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", true);

        message.setNonce(17L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");


        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("ggxYIG4FXXTBIHLVG6W8Drv5xISY0g0KEHQEzwXuSUPwolqc",message.getParams());
        Assert.assertEquals("vW82qMgN8BpP0BranFhSXdHm/uRPQNuYtshh4Inn1l0yKAwWp7adP42Y5ysN3B7EW4TMotPzMGsWjE0Y9hOhEAE=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigRemoveApprove() throws IOException {

//        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String signer = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));

        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiApproveMsgBuilder.msigRemoveApprove(multiAddress, signer, 13L, proposerIDAddress,"t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", true);

        message.setNonce(12L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");


        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("gg1YIA3rWyQoYGbyO9SzvCZmN5D/Vlqas17mdCLxRDvvpZQS",message.getParams());
        Assert.assertEquals("eX7klqmyx28PBiiPrgtzK28VLQ8mK0aV8wHcIsp46l9zRvt/LEWyEHZmhfz5BZ54Fx80wYGpnhVms795e13NKQE=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigSwapApprove() throws IOException {

        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));

        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiApproveMsgBuilder.msigSwapApprove(multiAddress, signer, 15L, proposerIDAddress,"t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq", "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota");

        message.setNonce(19L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");


        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("gg9YIGhqfLFBRtt3coRO3UIy4zkRFZ6BiM8BIazSZeAtjHU7",message.getParams());
        Assert.assertEquals("TNCdlR1flclsl4wLOznPsS62VNzAmv+fw509o1O5Q58UjiQ3mGJyWW6v2BG6Nrg/fDYFd8z0Yoqzee/E1Qy6HwE=",signedMessage.getSignature().getData());

    }

    @Test
    public void export() throws DecoderException {
        String priKeyAsHex = "7524a04c0b43b15930610450dbf4991ff6f5368a1e4922933679cf2a3e6497c7";

        System.out.println(Base64.encodeBase64String(Hex.decodeHex(priKeyAsHex)));
    }

}
