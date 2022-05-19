package io.filecoin.crypto;

import com.alibaba.fastjson.JSON;
import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import org.junit.Assert;
import org.junit.Test;

public class SignedMessageTest {

    @Test
    public void toCid(){

        Message tran = new Message();
        tran.setFrom("f1icys7esrj6h5htoar45qv5hjhblniyvci5ygcqq");
        tran.setTo("f3ur5337bx37xfpyavzkgcqeae4hrdz6j4rgfer4qj4bu45o5twsqz46ztvfxivsgdkj6kliugnoxmq3ecqqnq");
        tran.setNonce(10L);
        tran.setValue("10000000000000");
        tran.setGasFeeCap("3336579406");
        tran.setGasLimit(2719272L);
        tran.setGasPremium("100541");
        tran.setMethod(0L);
        tran.setParams("");
        tran.setVersion(0L);
        Signature raw = new Signature();
        raw.setType(1);
        raw.setData("lKDbPLfZbVEJVUugzaazGnMpxePHQVVyFdmeom1gcZIxcjV5eT9FEz1HRfZVkrHBXQ37XG7NX/NfnUKX6dmFxwA=");

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(tran);
        signedMessage.setSignature(raw);

        System.out.println("signedMessage   - >  " + JSON.toJSONString(signedMessage));

        Cid cid = SignedMessage.toCid(signedMessage);

        System.out.println("cid   - >  " + cid.getStr());

        Assert.assertEquals("bafy2bzacebmxdfitxaopygbfxb2fwmxcdnuulct35g5ehaeohhwvka235saly", cid.getStr());

    }

}
