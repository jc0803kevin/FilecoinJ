package io.filecoin.tx.multisig;

import com.alibaba.fastjson.JSON;
import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.domain.types.TipSetKey;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class MultiCancelMsgBuilderTest extends BaseTest {

    private static String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
    private static String multiAddress = "t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q";
    private static String to = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";
    private static BigInteger toAmount = new BigInteger("100");

    @Test
    public void cancelTx() throws IOException {
        String signer = proposer;
        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiCancelMsgBuilder.cancelTx(multiAddress, signer, 16L);

        message.setNonce(103L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

//        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
//        System.out.println("RawResponse-->" + result.getRawResponse());

        Assert.assertEquals("ghBA",message.getParams());
        Assert.assertEquals("+ANvSa1PJwmPLjc9VcH2gwCCuVojWBAlYHi7FWVvdGNDmntmKWgfxZsaBBKTC1p9Lm5RZVPas3TtvTuTKPcJ7gE=",signedMessage.getSignature().getData());

    }

    @Test
    public void cancelTxWithHash() throws IOException {
        String signer = proposer;
        String proposerIDAddress = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();
        Message message = MultiCancelMsgBuilder.cancelTxWithHash(multiAddress, signer, proposerIDAddress, 17L, to, toAmount);

        message.setNonce(106L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(signer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

//        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
//        System.out.println("RawResponse-->" + result.getRawResponse());

        Assert.assertEquals("ghFYILithh5L9VdqHyfxVOcPDxkx0oIEJ8lOjGlZ4hfzAe7m",message.getParams());
        Assert.assertEquals("JBH5ZJkiNBf4mZYj5RQoeRCENnj2/sY7Im9UTUtXMlZZ+tx5jaO5UHM2eskThtNrHYavoX8t30SPQKzeKYC+HgA=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigAddCancel() throws IOException {

        Message message = MultiCancelMsgBuilder.msigAddCancel(multiAddress, proposer,  6L);

        message.setNonce(89L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("ggZA",message.getParams());
        Assert.assertEquals("XEqwTXFKBI9fjyWukUYM3FWGU7bSfKuz5nfmKfdY/wpSRzu5aUwCMLqPO2M9bxpKSlQLVoYXzPZ+BFOlPtnH0QA=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigAddCancelWithHash() throws IOException {

        Message message = MultiCancelMsgBuilder.msigAddCancelWithHash(multiAddress, proposer, "t023009", 11L, "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", true);

        message.setNonce(92L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("ggtYINzkseVmv0+y1dBZRBNz3vGrApKCr03lq9J9+dFD9Ypv",message.getParams());
        Assert.assertEquals("BV6VWL7Z7guVZkDJB5xjMw5nXuD1NjpQdwg2t9xow2Vsf+cCGMgOvoykF+GN55BDxlzfmQacqoUZfTg472qylgA=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigSwapCancel() throws IOException {

        Message message = MultiCancelMsgBuilder.msigSwapCancel(multiAddress, proposer, 14L, "t023009",
                "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq", "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota");

        message.setNonce(99L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("gg5YIGhqfLFBRtt3coRO3UIy4zkRFZ6BiM8BIazSZeAtjHU7",message.getParams());
        Assert.assertEquals("6rGN8LP7u4bKHNck9NAMRHhzyvNQTD+c7l5pGF0nWs8YJ2gKmGQqzffc5Drx9IXYb3P4BmQL5B2h/Z/LrDyk2AA=",signedMessage.getSignature().getData());

    }

}
