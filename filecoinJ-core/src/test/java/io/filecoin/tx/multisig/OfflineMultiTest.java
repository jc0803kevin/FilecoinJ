package io.filecoin.tx.multisig;

import io.filecoin.BaseTest;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.multi.MethodsMultisig;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.core.methods.response.MpoolPush;
import io.filecoin.protocol.domain.types.TipSetKey;
import io.filecoin.tx.multisig.types.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OfflineMultiTest extends BaseTest {

    private static List<String> signers = new ArrayList<>();
    private static String proposer = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
    private static String multiAddress = "t2ozj3ly5j6aiubw6whudsylwet2ykxxp3tlkff7q";
    private static String to = "t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra";

    static {
        signers.add("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        signers.add("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        signers.add("t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq");
    }

    @Test
    public void Create() throws IOException {

        Message message = new Message();
        message.setTo("t01");       //to地址固定t01
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(2L);      //方法编号2
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(proposer).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        CreateMultisigMsgParam param = new CreateMultisigMsgParam();
        param.setCodeCID("fil/6/multisig");
        param.setRequiredNumberOfApprovals(2);
        param.setSigners(signers);
        param.setStartEpoch(0);
        param.setUnlockDuration(0);
        String params = Base64.encodeBase64String(param.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzaceckphszqllfk6ha2zbp622sfc73gie3ge4wvx4ctrd3dmudpcbmde
    }

    @Test
    public void Propose() throws IOException {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(2L);      //方法编号2
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(proposer).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        ProposeParams param = new ProposeParams();
        param.setTo(to);
        param.setValue(new BigInteger("100"));

        String params = Base64.encodeBase64String(param.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(proposer)));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzacedd2lkbpd4bfe6prb2yxs7lvfgrf7eys5vgghzgyyliqfzw5m4zae
    }

    @Test
    public void Approve() throws IOException {
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Approve.getMethod());      //方法编号3
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(message.getFrom()).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        ProposalHashData param = new ProposalHashData();
        param.setTo(to);
        param.setValue(new BigInteger("100"));
        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setTxID(0L);

        String params = Base64.encodeBase64String(param.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(message.getFrom())));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzaceccwkp33vs4wocz7kyluaka5rmgordis3ufvnfs26i7ittkkk5dc4
    }

    @Test
    public void Cancle() throws IOException {
        //proposer bafy2bzacecy7tds67ice57twczezvittzgrrvjdzai4avan5uqdo7uxfamyyq
        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer); // only proposer do cancle
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(message.getFrom()).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        ProposalHashData param = new ProposalHashData();
        param.setTo(to);
        param.setValue(new BigInteger("100"));
        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setTxID(1L);

        String params = Base64.encodeBase64String(param.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(message.getFrom())));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //
    }

    @Test
    public void MsigAddPropose() throws IOException {

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer); // only proposer do cancle
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Propose.getMethod());
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(message.getFrom()).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        addSignerParams.setIncrease(true);

        System.out.println("addSignerParams-->" + Hex.encodeHexString(addSignerParams.marshalCBOR()));

        ProposeParams proposeParams = new ProposeParams();
        proposeParams.setTo(multiAddress);
        proposeParams.setMethod(Long.valueOf(MethodsMultisig.AddSigner.getMethod()).intValue());
        proposeParams.setParams(Base64.encodeBase64String(addSignerParams.marshalCBOR()));
        proposeParams.setValue(new BigInteger("0"));

        System.out.println("proposeParams-->" + Hex.encodeHexString(proposeParams.marshalCBOR()));

        String params = Base64.encodeBase64String(proposeParams.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(message.getFrom())));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzacecabnh37tyuvarnb5xxzmf4l5s4n6hrpkpnxgnzu5ywju5m56bloa
    }

    @Test
    public void MsigAddCancel() throws IOException {

        String can = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";

        // 先使用 node 构建，然后对比

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(message.getFrom()).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        addSignerParams.setIncrease(true);

        System.out.println("addSignerParams-->" + Hex.encodeHexString(addSignerParams.marshalCBOR()));

        ProposalHashData param = new ProposalHashData();
        param.setTo(multiAddress);//
        param.setValue(new BigInteger("0"));
//        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setTxID(3L);
        param.setMethod(Long.valueOf(MethodsMultisig.AddSigner.getMethod()));
        param.setParams(Base64.encodeBase64String(addSignerParams.marshalCBOR()));

        System.out.println("proposeParams-->" + Hex.encodeHexString(param.marshalCBOR()));

        TxnIDParams txnIDParams = new TxnIDParams();
        txnIDParams.setTxnID(param.getTxID());
        txnIDParams.setProposalHash(Base64.encodeBase64String(param.marshalCBOR()));

        String params = Base64.encodeBase64String(txnIDParams.marshalCBOR());
        message.setParams(params);
        System.out.println("message params-->" + params);
        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(message.getFrom())));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        //MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        //System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzaceaqjkjeprm3exas5ufi7ot6gp4efidkswrcw7jd4tuadand2zf44y
    }

    @Test
    public void MsigAddCancel2() throws IOException {

        String can = "t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq";

        Message message = new Message();
        message.setTo(multiAddress);
        message.setFrom(proposer);
        message.setVersion(0L);
        message.setMethod(MethodsMultisig.Cancel.getMethod());
        message.setValue("0");
        message.setNonce(filecoinJ.MpoolGetNonce(message.getFrom()).send().getResult());
        message.setGasFeeCap("2554952971");
        message.setGasLimit(18103918L);
        message.setGasPremium("150790");

        AddSignerParams addSignerParams = new AddSignerParams();
        addSignerParams.setNewSigner("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");
        addSignerParams.setIncrease(false);

        System.out.println("addSignerParams-->" + Hex.encodeHexString(addSignerParams.marshalCBOR()));

        ProposalHashData param = new ProposalHashData();
        param.setTo(multiAddress);
        param.setValue(new BigInteger("0"));
//        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setRequester(filecoinJ.StateLookupID(proposer, TipSetKey.empty()).send().getResult());
        param.setTxID(2L);
//        param.setMethod(2);
        param.setMethod(Long.valueOf(MethodsMultisig.AddSigner.getMethod()));
        param.setParams(Base64.encodeBase64String(addSignerParams.marshalCBOR()));

        System.out.println("proposeParams-->" + Hex.encodeHexString(param.marshalCBOR()));

        String params = Base64.encodeBase64String(param.marshalCBOR());

        message.setParams(params);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(message.getFrom())));
        Signature signature = Signer.sign(message, priKeyAsHex);

        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());

        //bafy2bzacedun22grb53iubvqqrmk6evvqjlykd6hcjxhjfdz5hzd6fwvpnyoi
    }
}
