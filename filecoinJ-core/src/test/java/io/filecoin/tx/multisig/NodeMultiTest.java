package io.filecoin.tx.multisig;

import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.protocol.core.methods.response.BigintResp;
import io.filecoin.protocol.core.methods.response.msig.MsigGetPending;
import io.filecoin.protocol.core.methods.response.msig.MsigGetVestingSchedule;
import io.filecoin.protocol.core.methods.response.msig.MultiMessagePrototype;
import io.filecoin.protocol.domain.types.TipSetKey;
import io.filecoin.protocol.utils.JsonUtils;
import io.filecoin.tx.multisig.types.ProposalHashData;
import io.filecoin.tx.multisig.types.TxnIDParams;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

@Ignore
public class NodeMultiTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(NodeMultiTest.class);

    private String multiAddress = "t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q";

    @Test
    public void MsigGetAvailableBalance() throws IOException {
        TipSetKey tsk = new TipSetKey();
        BigintResp result = filecoinJ.MsigGetAvailableBalance(multiAddress, tsk).send();

        System.out.println("MsigGetAvailableBalance result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigGetVestingSchedule() throws IOException {
        TipSetKey tsk = new TipSetKey();
        MsigGetVestingSchedule result = filecoinJ.MsigGetVestingSchedule(multiAddress, tsk).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigGetVestingSchedule result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigGetVested() throws IOException {
        TipSetKey tsk = new TipSetKey();
        BigintResp result = filecoinJ.MsigGetVested(multiAddress, tsk, tsk).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigGetPending() throws IOException {
        TipSetKey tsk = new TipSetKey();
        MsigGetPending result = filecoinJ.MsigGetPending(multiAddress, tsk).send();

        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigCreate() throws IOException {
        Long required = 2L;
        MultiMessagePrototype result = filecoinJ.MsigCreate(required, signers, 0L, new BigInteger("0"), sender, BigInteger.ZERO).send();

        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigPropose() throws IOException {
        String recipient = "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota";
        BigInteger amount = new BigInteger("2");

        MultiMessagePrototype result = filecoinJ.MsigPropose(multiAddress, recipient, amount, sender, 3, "").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        //bafy2bzaced6hwgmep2wpveqds4zwvw2ckqgwdnnozlkax4zqmmpjlussrxpha
        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigApprove() throws IOException {
        Long proposedId = 2L;
        String signer = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";
        MultiMessagePrototype result = filecoinJ.MsigApprove(multiAddress, proposedId, signer).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        //bafy2bzaceckbarg5wkrpi552adlakr7oao6dq7ojpfh3nuib6yo64cp6cztsw
        //bafy2bzacecin4bksqqqej3y7h2qa3buvo7ga3jd3k6x5omsmilnt3aiyzjrja out of gas
        //bafy2bzacebat76tig5vmq4nte5tosxzorfpxorfrvoisrazgbddsnazx5hjww
        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigApproveTxnHash() throws IOException {
        Long proposedId = 1L;
        String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        String recipient = "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota";
        BigInteger amount = new BigInteger("2");
        String signer = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";
        MultiMessagePrototype result = filecoinJ.MsigApproveTxnHash(multiAddress, proposedId, proposer, recipient, amount, signer, 4, "").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigCancel() throws IOException {
        Long proposedId = 1L;
        String recipient = "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota";
        BigInteger amount = new BigInteger("2");
        String signer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MultiMessagePrototype result = filecoinJ.MsigCancel(multiAddress, proposedId, recipient, amount, signer, 4, "").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigCancel result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigAddPropose() throws IOException {
        String newSigner = "t1inf3slcpqhlvwgeewteqt6c6wm7h4snwxw7ssvy";
        String signer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MultiMessagePrototype result = filecoinJ.MsigAddPropose(multiAddress, signer, newSigner, true).send();

        System.out.println("MsigAddPropose result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigAddApprove() throws IOException {
        Long proposedId = 5L;
        String newSigner = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";
        String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        MultiMessagePrototype result = filecoinJ.MsigAddApprove(multiAddress, signer, proposedId, proposer, newSigner, true).send();

        System.out.println("MsigAddApprove result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigAddCancel() throws IOException {
        Long proposedId = 2L;
        String newSigner = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";
//        String signer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        MultiMessagePrototype result = filecoinJ.MsigAddCancel(multiAddress, signer, proposedId, newSigner, true).send();

        System.out.println("MsigAddCancel result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigSwapPropose() throws IOException {
        String newSigner = "t14rjfioypajbnultdrf67jsbi5iefvdom6ogtw7a";
        String oldSigner = "t1zklp6kwkb74r6tlsny47q5vubtqp7k3cxrvclda";
        String signer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MultiMessagePrototype result = filecoinJ.MsigSwapPropose(multiAddress, signer, oldSigner, newSigner).send();

        System.out.println("MsigSwapPropose result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigSwapApprove() throws IOException {
        Long proposedId = 7L;
        String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        String newSigner = "t1zklp6kwkb74r6tlsny47q5vubtqp7k3cxrvclda";
        String oldSigner = "t1vnqxq3326j4strwrfvz5tnmgj744jin4i4aiz6i";
//        String signer = "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri";
        String signer = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";
        MultiMessagePrototype result = filecoinJ.MsigSwapApprove(multiAddress, signer, proposedId, proposer, oldSigner, newSigner).send();

        System.out.println("MsigSwapApprove result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigSwapCancel() throws IOException {
        Long proposedId = 8L;
        String newSigner = "t14rjfioypajbnultdrf67jsbi5iefvdom6ogtw7a";
        String oldSigner = "t1zklp6kwkb74r6tlsny47q5vubtqp7k3cxrvclda";
        String signer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MultiMessagePrototype result = filecoinJ.MsigSwapCancel(multiAddress, signer, proposedId, oldSigner, newSigner).send();

        System.out.println("MsigSwapCancel result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigRemoveSigner() throws IOException {
        String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        String toRemove = "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota";
        MultiMessagePrototype result = filecoinJ.MsigRemoveSigner(multiAddress, proposer, toRemove, false).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigSwapCancel result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MsigApproveTxnHash2() throws IOException {
        Long proposedId = 7L;
        String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";

        String recipient = "t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota";
        BigInteger amount = new BigInteger("2");

        String proposerIDAddres = filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult();

        ProposalHashData param = new ProposalHashData();
        param.setTo(recipient);
        param.setValue(amount);
        param.setRequester(proposerIDAddres);
        param.setTxID(proposedId);
        param.setMethod(0L);
        param.setParams(Base64.getEncoder().encodeToString(new byte[]{}));

        System.out.println(JsonUtils.toJsonString(param));

        byte[] hash =  Signer.createHash(param.marshalCBOR());

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(proposedId);
        txnIDParams.setProposalHash(org.apache.commons.codec.binary.Base64.encodeBase64String(hash));

        //String params = org.apache.commons.codec.binary.Base64.encodeBase64String(txnIDParams.marshalCBOR());

        String signer = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";

        //bafy2bzacebzc33w2ju3knb4ci6ucnqoko6udnp2m5bcgiwm5njlbhlwmmgpny
        MultiMessagePrototype result = filecoinJ.MsigApproveTxnHash(multiAddress, proposedId, proposerIDAddres, recipient, amount, signer,
                3, Base64.getEncoder().encodeToString(new byte[]{})).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("MsigGetVested result-->" + JsonUtils.toJsonString(result.getResult()));
    }
}
