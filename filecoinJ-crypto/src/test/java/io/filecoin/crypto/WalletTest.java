package io.filecoin.crypto;

import org.bitcoinj.core.ECKey;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class WalletTest {

    @Test
    public void getBip44Credentials(){
        String address = Wallet.getBip44Credentials("ripple scissors kick mammal hire column oak again sun offer wealth tomorrow wagon turn fatal");

        Assert.assertEquals("f1sn5jluvvujjvepzfjksnng2on7v4maa6ieljx4y", address);
    }

    @Test
    public void fromPub(){
        ECKey ecKey = ECKey.fromPrivate(new BigInteger("1d969865e189957b9824bd34f26d5cbf357fda1a6d844cbf0c9ab1ed93fa7dbe", 16), false);

        String pubKeyAsHex = ecKey.getPublicKeyAsHex();

        String address = Wallet.fromPub(pubKeyAsHex, NetworkParameters.MAIN_NET);

        Assert.assertEquals("f1z4a36sc7mfbv4z3qwutblp2flycdui3baffytbq", address);
    }
}
