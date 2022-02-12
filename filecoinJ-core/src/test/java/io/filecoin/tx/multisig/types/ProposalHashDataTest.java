package io.filecoin.tx.multisig.types;

import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.utils.Convert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;

public class ProposalHashDataTest {

    @Test
    public void marshalCBOR() {
        ProposalHashData hashData = new ProposalHashData();
        hashData.setTxID(3L);
        hashData.setRequester("t023009");
        hashData.setTo("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        hashData.setMethod(MethodsMultisig.AddSigner.getMethod());//
        hashData.setValue(new BigInteger("0"));


        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        addSignerParams.setIncrease(true);

        hashData.setParams(Base64.getEncoder().encodeToString(addSignerParams.marshalCBOR()));

        System.out.println(Base64.getEncoder().encodeToString(hashData.marshalCBOR()));
    }


    @Test
    public void marshalCBOR2() {
        ProposalHashData hashData = new ProposalHashData();
        hashData.setTxID(0L);
        hashData.setRequester("t030148");
        hashData.setTo("t13jwpf2ydhxzxg6aafqxpyye4tyxuhooyxtfueby");
        hashData.setMethod(MethodsMultisig.Approve.getMethod());
        hashData.setValue(Convert.toAttoFIL(new BigDecimal("10.5"), Convert.Unit.FIL).toBigInteger());
        hashData.setParams(Base64.getEncoder().encodeToString(new byte[]{}));

        System.out.println(Base64.getEncoder().encodeToString(hashData.marshalCBOR()));
    }
}
