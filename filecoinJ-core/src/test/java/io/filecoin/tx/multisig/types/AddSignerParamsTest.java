package io.filecoin.tx.multisig.types;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Assert;
import org.junit.Test;

public class AddSignerParamsTest {

    @Test
    public void marshalCBOR(){

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        addSignerParams.setIncrease(true);

//        8255017340a03feea544ee65030e12ed3b18c71e89269cf4
//        8255017340a03feea544ee65030e12ed3b18c71e89269cf5
        Assert.assertEquals("8255017340a03feea544ee65030e12ed3b18c71e89269cf5", Hex.toHexString(addSignerParams.marshalCBOR()));
    }

}
