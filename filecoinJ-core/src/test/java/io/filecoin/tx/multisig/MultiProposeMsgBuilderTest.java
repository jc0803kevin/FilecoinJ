package io.filecoin.tx.multisig;

import com.alibaba.fastjson.JSON;
import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class MultiProposeMsgBuilderTest extends BaseTest {

    private static String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
    private static String multiAddress = "t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q";
    private static String to = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";
    private static BigInteger toAmount = new BigInteger("100");

    @Test
    public void proposeTx() throws IOException {
        Message message = MultiProposeMsgBuilder.proposeTx(multiAddress,proposer, to, toAmount);

        message.setNonce(101L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("hFUBc0CgP+6lRO5lAw4S7TsYxx6JJpxCAGQAQA==",message.getParams());
        Assert.assertEquals("ApRu1qP2bt7J5p9HlVKMyuCBdbEFxw4hx/IFn3MbYxdF/IH9Xtzge3WGqQUJXq2IH41KBm7b6fuK39TyB1NHuwE=",signedMessage.getSignature().getData());

    }


    @Test
    public void msigAddPropose() throws IOException {
        Message message = MultiProposeMsgBuilder.msigAddPropose(multiAddress,proposer,
                "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", true);

        message.setNonce(77L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        //bafy2bzaceaiuba52fpfxgtmt6hpr4tulgwz4hhqi3ns4xssun7v6ctlo5mb3i
        //{"message":{"from":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","gasFeeCap":"2554952971","gasLimit":18103918,"gasPremium":"150790","method":2,"nonce":77,"params":"hFUCdlO146nwEUDb1j0HLC7EnrCr3ftAAFgYglUBWE/E+3sCaCDsTRUoIcc8B6YJmbD1","to":"t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q","value":"0","version":0},"signature":{"data":"D9PUUtY4bf2iEwFP07f6uugtcAjlJV+rhktHvlWYrDgySiI65VaFMNyigL8PyAs55WOvMrGSvfrKpaZJ/K5LBgE=","type":1}}
        Assert.assertEquals("hFUCdlO146nwEUDb1j0HLC7EnrCr3ftAAFgYglUBWE/E+3sCaCDsTRUoIcc8B6YJmbD1",message.getParams());
        Assert.assertEquals("D9PUUtY4bf2iEwFP07f6uugtcAjlJV+rhktHvlWYrDgySiI65VaFMNyigL8PyAs55WOvMrGSvfrKpaZJ/K5LBgE=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigRemoveSigner() throws IOException {
        Message message = MultiProposeMsgBuilder.msigRemoveSigner(multiAddress,proposer,
                "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", true);

        message.setNonce(94L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("hFUCdlO146nwEUDb1j0HLC7EnrCr3ftABlgYglUBWE/E+3sCaCDsTRUoIcc8B6YJmbD1",message.getParams());
        Assert.assertEquals("Y9xSyep8E5AnL0HeCRRvKnNbhThtJgp6DE/hbTroTjoE4ZwsSdNGuSoH3fXOlfiM4Fo4MzLzAbNAoVlQQfUbygE=",signedMessage.getSignature().getData());

    }

    @Test
    public void msigSwapPropose() throws IOException {
        Message message = MultiProposeMsgBuilder.msigSwapPropose(multiAddress,proposer,
                "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq", "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota");

        message.setNonce(98L);
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);
        System.out.println("signedMessage-->" + JSON.toJSONString(signedMessage));

        Assert.assertEquals("hFUCdlO146nwEUDb1j0HLC7EnrCr3ftAB1gtglUB9AuQqtTOGE4KS1O72hB3CgL+s9FVAVhPxPt7Amgg7E0VKCHHPAemCZmw",message.getParams());
        Assert.assertEquals("TtxM+goBn4EEMdl6DqQ73/1FOk3qi1W9ASIrTJP/6JIB5pV02JpUt5ghk29o6a1mFw/mjrx7Ucc8+OdLiTghVwA=",signedMessage.getSignature().getData());

    }
}
