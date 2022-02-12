package io.filecoin;

import io.filecoin.crypto.Signer;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

public class TransferTest extends BaseTest {

    @Test
    public void signTransaction() {

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";

        Message tran = new Message();
        tran.setFrom(from);
        tran.setTo("t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota");
        tran.setNonce(0L);
        tran.setValue("1000000000000");//0.000001
        tran.setGasFeeCap("2554952971");
        tran.setGasLimit(2103918L);
        tran.setGasPremium("120630");
        tran.setMethod(0L);
        tran.setParams("");
        tran.setVersion(0L);
        Signature raw = Signer.sign(tran, Hex.toHexString(Base64.decode(signerPriKeys.get(from))));
        System.out.println("raw   - >  " + raw.getData());

        Assert.assertEquals("AOUxRxa9eN14QWWc7xKfVDKUrGNVtzc4rmZm44pnWlsK6jhfedqwFWQ862wEHEpaZGfoSZmJEEpbGUVZjcA4DAA=", raw.getData());

    }

}
