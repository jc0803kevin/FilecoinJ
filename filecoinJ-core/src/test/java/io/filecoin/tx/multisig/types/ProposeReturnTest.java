package io.filecoin.tx.multisig.types;

import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

public class ProposeReturnTest {

    @Test
    public void unmarshalCBOR() {

        ProposeReturn proposeReturn = new ProposeReturn();

        //{"applied":false,"exitCode":0,"ret":"","txnID":37}
        proposeReturn = proposeReturn.unmarshalCBOR(Base64.getDecoder().decode("hBgl9ABA"));

        Assert.assertEquals(false, proposeReturn.isApplied());
        Assert.assertEquals(37L, proposeReturn.getTxnID().longValue());
        Assert.assertEquals(0, proposeReturn.getExitCode().longValue());
        Assert.assertEquals("", proposeReturn.getRet());
    }

}
