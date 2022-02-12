package io.filecoin;

import com.google.common.collect.Lists;
import io.filecoin.crypto.Signer;
import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.core.methods.request.MessageSendSpec;
import io.filecoin.protocol.core.methods.response.*;
import io.filecoin.protocol.domain.types.KeyInfo;
import io.filecoin.protocol.domain.types.MessagePrototype;
import io.filecoin.protocol.domain.types.TipSet;
import io.filecoin.protocol.domain.types.TipSetKey;
import io.filecoin.protocol.utils.JsonUtils;
import io.filecoin.utils.Convert;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Ignore
public class FileCoinJTest extends BaseTest{
    private static final Logger log = LoggerFactory.getLogger(FileCoinJTest.class);

    @Test
    public void getId() throws Exception{
        ID id = filecoinJ.id().send();
        log.info("getId result ->{}",id.getID());
    }


    @Test
    public void getVersion() throws Exception{
        FilecoinClientVersion version = filecoinJ.getVersion().send();
        System.out.println("getVersion result-->" + JsonUtils.toJsonString(version.getClientVersion()));
    }

    @Test
    public void chainHead() throws Exception{
        ChainHead result = filecoinJ.chainHead().send();
        System.out.println("chainHead result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetGenesis() throws Exception{
        Response<TipSet> result = filecoinJ.ChainGetGenesis().send();
        System.out.println("getGenesis result-->" + JsonUtils.toJsonString(result.getResult()));
    }


    @Test
    public void ChainGetBlock() throws Exception{
        Cid cid = new Cid();
        cid.setStr("bafy2bzacecbnleaqakhgivdkoyqhrmturnxx6kad425uwyyonw2h4iqsv3m3g");
        ChainGetBlock result = filecoinJ.ChainGetBlock(cid).send();
        System.out.println("ChainGetBlock result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetBlockMessages() throws Exception{
        Cid cid = new Cid();
        cid.setStr("bafy2bzacecbnleaqakhgivdkoyqhrmturnxx6kad425uwyyonw2h4iqsv3m3g");
        ChainGetBlockMessages result = filecoinJ.ChainGetBlockMessages(cid).send();
        //System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainGetBlockMessages result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetParentReceipts() throws Exception{
        Cid cid = Cid.of("bafy2bzacecbnleaqakhgivdkoyqhrmturnxx6kad425uwyyonw2h4iqsv3m3g");
        ChainGetParentReceipts result = filecoinJ.ChainGetParentReceipts(cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainGetParentReceipts result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetParentMessages() throws Exception{
        Cid cid = Cid.of("bafy2bzacecbnleaqakhgivdkoyqhrmturnxx6kad425uwyyonw2h4iqsv3m3g");
        ChainGetParentMessages result = filecoinJ.ChainGetParentMessages(cid).send();
        System.out.println("ChainGetParentMessages result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetTipSetByHeight() throws Exception{
        TipSetKey tsk = new TipSetKey();
        ChainGetTipSetByHeight result = filecoinJ.ChainGetTipSetByHeight(419110L, tsk).send();
        System.out.println("ChainGetTipSetByHeight result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainReadObj() throws Exception{
        Cid cid = Cid.of("bafy2bzaceca3zf4tyjqusxrmobs5a7oxd5e647rpp3tmo45n7tob6pewilopy");
        ChainReadObj result = filecoinJ.ChainReadObj(cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainReadObj result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainDeleteObj() throws Exception{
        Cid cid = Cid.of("bafy2bzacedrrginj3mxpgpc7ak22gkg57rejeasihwivyclb2d7sygswm4jgi");
        Response result = filecoinJ.ChainDeleteObj(cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainDeleteObj result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainHasObj() throws Exception{
        Cid cid = Cid.of("bafy2bzaceca3zf4tyjqusxrmobs5a7oxd5e647rpp3tmo45n7tob6pewilopy");
        ChainHasObj result = filecoinJ.ChainHasObj(cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainHasObj result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult());
    }

    @Test
    public void ChainStatObj() throws Exception{
        Cid cid = Cid.of("bafy2bzaceca3zf4tyjqusxrmobs5a7oxd5e647rpp3tmo45n7tob6pewilopy");
        Response result = filecoinJ.ChainStatObj(cid, cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ChainSetHead() throws Exception{
        TipSetKey tsk = new TipSetKey();
        Response result = filecoinJ.ChainSetHead(tsk).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ChainTipSetWeight() throws Exception{
        TipSetKey tsk = new TipSetKey();
        ChainTipSetWeight result = filecoinJ.ChainTipSetWeight(tsk).send();
        System.out.println("ChainTipSetWeight-->" + JsonUtils.toJsonString(result.getResult()));
    }


    @Test
    public void ChainGetNode() throws Exception{
        IpldObject result = filecoinJ.ChainGetNode("/ipld/12D3KooWRcJ86W7a3FjaoCBuuiG5NBVg14r5Sixuac9eLhG7SBGH/").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainGetNode result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetMessage() throws Exception{
        Cid cid = Cid.of("bafy2bzaced3ob74jjd23kuq5lutazgwh6mo6ma6mcpneudjbt4ugl73lbovec");
        ChainGetMessage result = filecoinJ.ChainGetMessage(cid).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainGetMessage result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ChainGetPath() throws Exception{
        ChainGetPath result = filecoinJ.ChainGetPath(new TipSetKey(), new TipSetKey()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("ChainGetMessage result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void BeaconGetEntry() throws Exception{
        BeaconGetEntry result = filecoinJ.BeaconGetEntry(0L).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("BeaconGetEntry result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void GasEstimateFeeCap() throws Exception{

        Message message = new Message();
        message.setFrom("");
        message.setTo("");
        message.setValue("1000");
//        message.setGasPremium(new BigInteger("99799"));
//        message.setGasFeeCap(new BigInteger("1883674893"));

        message.setGasPremium("0");
        message.setGasFeeCap("0");

        BigintResp result = filecoinJ.GasEstimateFeeCap(message, 1L, TipSetKey.empty()).send();
        System.out.println("BeaconGetEntry result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult().longValue() > 0);
    }

    @Test
    public void GasEstimateGasLimit() throws Exception{

        Message message = new Message();
        message.setFrom("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        message.setTo("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy");
        message.setValue("1000");
//        message.setGasPremium(new BigInteger("99799"));
//        message.setGasFeeCap(new BigInteger("1883674893"));

        message.setGasPremium("0");
        message.setGasFeeCap("0");

        GasEstimateGasLimit result = filecoinJ.GasEstimateGasLimit(message, TipSetKey.empty()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() > 0);
    }

    @Test
    public void GasEstimateGasPremium() throws Exception{

        Message message = new Message();
        message.setFrom("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        message.setTo("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy");
        message.setValue("1000");
//        message.setGasPremium(new BigInteger("99799"));
//        message.setGasFeeCap(new BigInteger("1883674893"));

        message.setGasPremium("0");
        message.setGasFeeCap("0");

        BigintResp result = filecoinJ.GasEstimateGasPremium(42L,"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", new Long("99799"), TipSetKey.empty()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult().longValue() > 0);
    }

    @Test
    public void GasEstimateMessageGas() throws Exception{

        Message message = new Message();
        message.setFrom("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        message.setTo("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy");
        message.setValue("1000");

        message.setGasPremium("0");
        message.setGasFeeCap("0");

        MessageSendSpec messageSendSpec = new MessageSendSpec();
        messageSendSpec.setMaxFee("50000000000");

        GasEstimateMessageGas result = filecoinJ.GasEstimateMessageGas(message,messageSendSpec, TipSetKey.empty()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void SyncState() throws Exception{

        SyncState result = filecoinJ.SyncState().send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolPending() throws Exception{

        MpoolPending result = filecoinJ.MpoolPending(TipSetKey.empty()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolSelect() throws Exception{

        MpoolSelect result = filecoinJ.MpoolSelect(TipSetKey.of(Cid.of("bafy2bzacebilaovzwid7bg7eal66w2l7mbgkvnty5afygkoefbgxfzpzd2rfi")), 2).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolPush() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        message.setValue(Convert.toAttoFIL(new BigDecimal("1"), Convert.Unit.FIL).toPlainString());
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(from)));
        Signature signature = Signer.sign(message, priKeyAsHex);


        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPush result = filecoinJ.MpoolPush(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolPushUntrusted() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        message.setValue("1");
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(from)));
        System.out.println("priKeyAsHex-->" + priKeyAsHex);
        Signature signature = Signer.sign(message, priKeyAsHex);


        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        MpoolPushUntrusted result = filecoinJ.MpoolPushUntrusted(signedMessage).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolBatchPushUntrusted() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        message.setValue("1");
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(from)));
        System.out.println("priKeyAsHex-->" + priKeyAsHex);
        Signature signature = Signer.sign(message, priKeyAsHex);


        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        List<SignedMessage> list = new ArrayList<>();
        list.add(signedMessage);

        MpoolBatchPushUntrusted result = filecoinJ.MpoolBatchPushUntrusted(list).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolPushMessage() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        message.setValue("1");
        message.setNonce(0L);
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);


        MessageSendSpec messageSendSpec = new MessageSendSpec();
        messageSendSpec.setMaxFee("50000000000");

        MpoolPushMessage result = filecoinJ.MpoolPushMessage(message, messageSendSpec).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolBatchPushMessage() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        message.setValue("1");
        message.setNonce(0L);
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        List<Message> list = new ArrayList<>();
        list.add(message);

        MessageSendSpec messageSendSpec = new MessageSendSpec();
        messageSendSpec.setMaxFee("50000000000");

        MpoolBatchPushMessage result = filecoinJ.MpoolBatchPushMessage(list, messageSendSpec).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolBatchPush() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        message.setValue("1");
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        String priKeyAsHex = Hex.encodeHexString(Base64.decodeBase64(signerPriKeys.get(from)));
        System.out.println("priKeyAsHex-->" + priKeyAsHex);
        Signature signature = Signer.sign(message, priKeyAsHex);


        SignedMessage signedMessage = new SignedMessage();
        signedMessage.setMessage(message);
        signedMessage.setSignature(signature);

        List<SignedMessage> list = new ArrayList<>();
        list.add(signedMessage);

        MpoolBatchPush result = filecoinJ.MpoolBatchPush(list).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolGetNonce() throws Exception{

        MpoolGetNonce result = filecoinJ.MpoolGetNonce("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolCheckPendingMessages() throws Exception{

        MessageCheckStatus result = filecoinJ.MpoolCheckPendingMessages("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolCheckMessages() throws Exception{
        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();
        Message message = new Message();
        message.setFrom(from);
        message.setTo("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        message.setValue(Convert.toAttoFIL(new BigDecimal("1"), Convert.Unit.FIL).toPlainString());
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);

        MessagePrototype messagePrototype = new MessagePrototype();
        messagePrototype.setMessage(message);

        MessageCheckStatus result = filecoinJ.MpoolCheckMessages(Lists.newArrayList(messagePrototype)).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolCheckReplaceMessages() throws Exception{
        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();
        Message message = new Message();
        message.setFrom(from);
        message.setTo("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        message.setValue(Convert.toAttoFIL(new BigDecimal("1"), Convert.Unit.FIL).toPlainString());
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(0L);


        MessageCheckStatus result = filecoinJ.MpoolCheckReplaceMessages(Lists.newArrayList(message)).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolClear() throws Exception{

        Response result = filecoinJ.MpoolClear(true).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void MpoolGetConfig() throws Exception{

        MpoolConfig result = filecoinJ.MpoolGetConfig().send();
//        {"jsonrpc":"2.0","result":{"PriorityAddrs":null,"SizeLimitHigh":30000,"SizeLimitLow":20000,"ReplaceByFeeRatio":1.25,"PruneCooldown":60000000000,"GasLimitOverestimation":1.25},"id":0}
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void MpoolSetConfig() throws Exception{
        MpoolConfig.Config config = new MpoolConfig.Config();
        config.setReplaceByFeeRatio(1.26);
        config.setGasLimitOverestimation(1.26);
        config.setSizeLimitHigh(30000);
        config.setSizeLimitLow(20000);
        config.setPruneCooldown(60000000000L);
        Response result = filecoinJ.MpoolSetConfig(config).send();
//        {"jsonrpc":"2.0","result":{"PriorityAddrs":null,"SizeLimitHigh":0,"SizeLimitLow":0,"ReplaceByFeeRatio":1.26,"PruneCooldown":0,"GasLimitOverestimation":1.26},"id":0}
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void MinerGetBaseInfo() throws Exception{

        MinerGetBaseInfo result = filecoinJ.MinerGetBaseInfo("t024486", 0L, TipSetKey.empty()).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }




    ////////////////////////wallet///////////////////////////
    @Test
    public void WalletNew() throws Exception{
        WalletNew result = filecoinJ.WalletNew("secp256k1").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletHas() throws Exception{
        WalletHas result = filecoinJ.WalletHas("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletList() throws Exception{
        WalletList result = filecoinJ.WalletList().send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletBalance() throws Exception{
        BigintResp result = filecoinJ.WalletBalance("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletSign() throws Exception{

        WalletSign result = filecoinJ.WalletSign(
                "t1a5b5swveglhoaqgmi3b2i4fjsgfb5dxvrkfcnoy", Base64.encodeBase64String("kevin".getBytes())).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletSignMessage() throws Exception{

        String from = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        MpoolGetNonce mpoolGetNonce = filecoinJ.MpoolGetNonce(from).send();

        Message message = new Message();
        message.setFrom(from);
        message.setTo("t3wp5dy4laxr3fpi2qzeo76wl5uclek7bgjafodyxfv5xe2z3etbniagqr3nt3pufiao4ui6m6n3vulfejunka");
        message.setValue("10000000000");
        message.setNonce(mpoolGetNonce.getResult());
        message.setParams("");
        message.setGasFeeCap("2554952971");
        message.setGasPremium("150790");
        message.setGasLimit(18103918L);
        message.setVersion(0L);
        message.setMethod(2L);

        WalletSignMessage result = filecoinJ.WalletSignMessage(from, message).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("getCid-->" + result.getResult().getMessage().getCid().getStr());
        System.out.println("getCid-->" + result.getResult().getCid().getStr());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletVerify() throws Exception{

        Signature signature = new Signature(1, "m4Aoyya6FNuasLU278RXuduQuwPj5IkusDLVxQJvK6s7CTdVaABPmjM1sTERu0xk70/v/1R07YvrX1lS8G4HRAE=");

        WalletVerify result = filecoinJ.WalletVerify("t1a5b5swveglhoaqgmi3b2i4fjsgfb5dxvrkfcnoy",
                Base64.encodeBase64String("kevin".getBytes()),signature
                ).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult());
    }

    @Test
    public void WalletDefaultAddress() throws Exception{

        WalletDefaultAddress result = filecoinJ.WalletDefaultAddress().send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletSetDefault() throws Exception{

        Response result = filecoinJ.WalletSetDefault("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void WalletExport() throws Exception{

        WalletExport result = filecoinJ.WalletExport("t1vnqxq3326j4strwrfvz5tnmgj744jin4i4aiz6i").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletImport() throws Exception{
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType("secp256k1");
        keyInfo.setPrivateKey("QxcZiwl0vKlx3+QJe58/hyL7SEDxI6U/sbOvdwqygqQ=");

        WalletImport result = filecoinJ.WalletImport(keyInfo).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void WalletDelete() throws Exception{
        Response result = filecoinJ.WalletDelete("t1xxewkurfvss5kerrhegd5k5nwnixqsxtjrbtrhy").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void WalletValidateAddress() throws Exception{
        WalletValidateAddress result = filecoinJ.WalletValidateAddress("f23643quwckqjrykwmptcvqiap6ph77y3a4yv4wqi").send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        Assert.assertTrue(result.getResult() != null);
    }

}
