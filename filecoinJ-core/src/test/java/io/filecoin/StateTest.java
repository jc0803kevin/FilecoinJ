package io.filecoin;

import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.core.methods.request.MessageMatch;
import io.filecoin.protocol.core.methods.response.*;
import io.filecoin.protocol.domain.types.TipSetKey;
import io.filecoin.protocol.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class StateTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(StateTest.class);

    @Test
    public void StateCall() throws Exception {

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

        StateCall result = filecoinJ.StateCall(tran, TipSetKey.empty()).send();
//        {"jsonrpc":"2.0","result":{"MsgCid":{"/":"bafy2bzaceb3tgzultz2pdkkcmpscujrz5jz2m7f33pgnxymgzi5k73zptrygm"},"Msg":{"Version":0,"To":"t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota","From":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","Nonce":41,"Value":"1000000000000","GasLimit":2103918,"GasFeeCap":"2554952971","GasPremium":"120630","Method":0,"Params":"","CID":{"/":"bafy2bzaceb3tgzultz2pdkkcmpscujrz5jz2m7f33pgnxymgzi5k73zptrygm"}},"MsgRct":{"ExitCode":0,"Return":null,"GasUsed":0},"GasCost":{"Message":null,"GasUsed":"0","BaseFeeBurn":"0","OverEstimationBurn":"0","MinerPenalty":"0","MinerTip":"0","Refund":"0","TotalCost":"0"},"ExecutionTrace":{"Msg":{"Version":0,"To":"t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota","From":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","Nonce":41,"Value":"1000000000000","GasLimit":2103918,"GasFeeCap":"2554952971","GasPremium":"120630","Method":0,"Params":"","CID":{"/":"bafy2bzaceb3tgzultz2pdkkcmpscujrz5jz2m7f33pgnxymgzi5k73zptrygm"}},"MsgRct":{"ExitCode":0,"Return":null,"GasUsed":216405},"Error":"","Duration":27459862,"GasCharges":null,"Subcalls":null},"Error":"","Duration":27460243},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("StateCall result-->" + JsonUtils.toJsonString(result.getResult()));
    }


    @Test
    public void StateReplay() throws Exception {

        StateReplay result = filecoinJ.StateReplay(null, Cid.of("bafy2bzaceb3tgzultz2pdkkcmpscujrz5jz2m7f33pgnxymgzi5k73zptrygm")).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void StateGetActor() throws Exception {

        StateGetActor result = filecoinJ.StateGetActor("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", null).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void StateReadState() throws Exception {

        StateReadState result = filecoinJ.StateReadState("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", null).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void StateMinerPower() throws Exception {

        StateMinerPower result = filecoinJ.StateMinerPower("f0113169", null).send();
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
        Assert.assertTrue(result.getResult() != null);
    }

    @Test
    public void StateListMessages() throws Exception {
        MessageMatch match = new MessageMatch();
        match.setTo("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq");
        match.setFrom("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        StateListMessages result = filecoinJ.StateListMessages(match, TipSetKey.empty(), 0L).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateDecodeParams() throws Exception {
        Response result = filecoinJ.StateDecodeParams("t14cfforwzqsfc42ics4u5wpp3stshnoxej3f7uaq", 0L, "", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateNetworkName() throws Exception {
        StateNetworkName result = filecoinJ.StateNetworkName().send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerSectors() throws Exception {
        StateMinerActiveSectors result = filecoinJ.StateMinerSectors("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerActiveSectors() throws Exception {
        StateMinerActiveSectors result = filecoinJ.StateMinerActiveSectors("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerProvingDeadline() throws Exception {
        StateMinerProvingDeadline result = filecoinJ.StateMinerProvingDeadline("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerInfo() throws Exception {
        StateMinerInfo result = filecoinJ.StateMinerInfo("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerDeadlines() throws Exception {
        StateMinerDeadlines result = filecoinJ.StateMinerDeadlines("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerPartitions() throws Exception {
        StateMinerPartitions result = filecoinJ.StateMinerPartitions("t08334", 0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateAllMinerFaults() throws Exception {
        StateAllMinerFaults result = filecoinJ.StateAllMinerFaults(0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerPreCommitDepositForPower() throws Exception {
        BigintResp result = filecoinJ.StateMinerPreCommitDepositForPower("f01234", null, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerInitialPledgeCollateral() throws Exception {
        BigintResp result = filecoinJ.StateMinerInitialPledgeCollateral("f01234", null, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerAvailableBalance() throws Exception {
        BigintResp result = filecoinJ.StateMinerAvailableBalance("t08334", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerSectorAllocated() throws Exception {
        StateMinerSectorAllocated result = filecoinJ.StateMinerSectorAllocated("t08334", 0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSectorPreCommitInfo() throws Exception {
        StateSectorPreCommitInfo result = filecoinJ.StateSectorPreCommitInfo("t08334", 0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSectorGetInfo() throws Exception {
        StateSectorGetInfo result = filecoinJ.StateSectorGetInfo("t01", 0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSectorExpiration() throws Exception {
        StateSectorExpiration result = filecoinJ.StateSectorExpiration("t08334", 2L, TipSetKey.of(Cid.of("bafy2bzacebocfidvfhz5gjqehohagmeaycdcz242zudhay33s2254p2sff5ru"))).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSectorPartition() throws Exception {
        StateSectorPartition result = filecoinJ.StateSectorPartition("t08334", 1L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSearchMsg() throws Exception {
        StateSearchMsg result = filecoinJ.StateSearchMsg(Cid.of("bafy2bzaceaod27kapy7ianh6orsamxxuhmgywfsfrekqua2wpqyhojv2kvgha")).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateSearchMsgLimited() throws Exception {
        StateSearchMsg result = filecoinJ.StateSearchMsgLimited(Cid.of("bafy2bzaceaod27kapy7ianh6orsamxxuhmgywfsfrekqua2wpqyhojv2kvgha"), 0L).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateWaitMsg() throws Exception {
        StateWaitMsg result = filecoinJ.StateWaitMsg(Cid.of("bafy2bzaceaod27kapy7ianh6orsamxxuhmgywfsfrekqua2wpqyhojv2kvgha"), 0L).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateListMiners() throws Exception {
        StateListMiners result = filecoinJ.StateListMiners(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateListActors() throws Exception {
        StateListActors result = filecoinJ.StateListActors(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMarketBalance() throws Exception {
        StateMarketBalance result = filecoinJ.StateMarketBalance("t011303", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMarketParticipants() throws Exception {
        StateMarketParticipants result = filecoinJ.StateMarketParticipants(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMarketDeals() throws Exception {
        StateMarketDeals result = filecoinJ.StateMarketDeals(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMarketStorageDeal() throws Exception {
        StateMarketStorageDeal result = filecoinJ.StateMarketStorageDeal(0L, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateLookupID() throws Exception {
        StateLookupID result = filecoinJ.StateLookupID("t01000", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateAccountKey() throws Exception {
        StateAccountKey result = filecoinJ.StateAccountKey("t1kjugcomcz3i3fosbpvamn2qoqkmjb4xngbhmazy", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateMinerSectorCount() throws Exception {
        StateMinerSectorCount result = filecoinJ.StateMinerSectorCount("t024486", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateCompute() throws Exception {
        StateCompute result = filecoinJ.StateCompute(0L, null, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateVerifierStatus() throws Exception {
        BigintResp result = filecoinJ.StateVerifierStatus("t1kjugcomcz3i3fosbpvamn2qoqkmjb4xngbhmazy", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateVerifiedClientStatus() throws Exception {
        BigintResp result = filecoinJ.StateVerifiedClientStatus("t1kjugcomcz3i3fosbpvamn2qoqkmjb4xngbhmazy", TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateVerifiedRegistryRootKey() throws Exception {
        StateVerifiedRegistryRootKey result = filecoinJ.StateVerifiedRegistryRootKey(TipSetKey.empty()).send();
//        {"jsonrpc":"2.0","result":"t080","id":0}
        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateDealProviderCollateralBounds() throws Exception {
        StateDealProviderCollateralBounds result = filecoinJ.StateDealProviderCollateralBounds(0L, true, TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateCirculatingSupply() throws Exception {
        BigintResp result = filecoinJ.StateCirculatingSupply(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateVMCirculatingSupplyInternal() throws Exception {
        StateVMCirculatingSupplyInternal result = filecoinJ.StateVMCirculatingSupplyInternal(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void StateNetworkVersion() throws Exception {
        StateNetworkVersion result = filecoinJ.StateNetworkVersion(TipSetKey.empty()).send();

        System.out.println("RawResponse-->" + result.getRawResponse());
        System.out.println("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

}
