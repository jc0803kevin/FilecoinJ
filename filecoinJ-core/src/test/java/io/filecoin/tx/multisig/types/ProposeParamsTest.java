package io.filecoin.tx.multisig.types;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Base64;

public class ProposeParamsTest {

    @Test
    public void marshalCBOR() {
        ProposeParams hashData = new ProposeParams();
        hashData.setTo("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        hashData.setMethod(2);
        hashData.setValue(new BigDecimal("0.01").multiply(BigDecimal.TEN.pow(18)).toBigInteger());


        System.out.println(Base64.getEncoder().encodeToString(hashData.marshalCBOR()));
    }
}
