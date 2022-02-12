package io.filecoin.protocol.core;

import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.FileCoinJ;
import io.filecoin.protocol.FilecoinjService;
import io.filecoin.protocol.core.methods.request.*;
import io.filecoin.protocol.core.methods.response.*;
import io.filecoin.protocol.core.methods.response.msig.MsigGetPending;
import io.filecoin.protocol.core.methods.response.msig.MsigGetVestingSchedule;
import io.filecoin.protocol.core.methods.response.msig.MultiMessagePrototype;
import io.filecoin.protocol.domain.builtin.miner.SectorPreCommitInfo;
import io.filecoin.protocol.domain.paych.SignedVoucher;
import io.filecoin.protocol.domain.types.KeyInfo;
import io.filecoin.protocol.domain.types.MessagePrototype;
import io.filecoin.protocol.domain.types.TipSet;
import io.filecoin.protocol.domain.types.TipSetKey;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonRpc2_0FileCoinJ implements FileCoinJ {

    protected FilecoinjService filecoinjService;

    public JsonRpc2_0FileCoinJ(FilecoinjService filecoinjService) {
        this.filecoinjService = filecoinjService;
    }

    @Override
    public Request<?, ID> id() {
        return new Request<>(
                "Filecoin.ID",
                Collections.<String>emptyList(),
                filecoinjService,
                ID.class);
    }

    @Override
    public Request<?, FilecoinClientVersion> getVersion() {
        return new Request<>(
                "Filecoin.Version",
                Collections.<String>emptyList(),
                filecoinjService,
                FilecoinClientVersion.class);
    }

    @Override
    public Request<?, ChainHead> chainHead() {
        return new Request<>(
                "Filecoin.ChainHead",
                Collections.<String>emptyList(),
                filecoinjService,
                ChainHead.class);
    }

    @Override
    public Request<?, ChainGetBlock> ChainGetBlock(Cid cid) {
        return new Request<>(
                "Filecoin.ChainGetBlock",
                Arrays.asList(cid),
                filecoinjService,
                ChainGetBlock.class);
    }

    @Override
    public Request<?, ChainGetBlockMessages> ChainGetBlockMessages(Cid blockCid) {
        return new Request<>(
                "Filecoin.ChainGetBlockMessages",
                Arrays.asList(blockCid),
                filecoinjService,
                ChainGetBlockMessages.class);
    }

    @Override
    public Request<?, ChainGetParentReceipts> ChainGetParentReceipts(Cid blockCid) {
        return new Request<>(
                "Filecoin.ChainGetParentReceipts",
                Arrays.asList(blockCid),
                filecoinjService,
                ChainGetParentReceipts.class);
    }

    @Override
    public Request<?, ChainGetParentMessages> ChainGetParentMessages(Cid blockCid) {
        return new Request<>(
                "Filecoin.ChainGetParentMessages",
                Arrays.asList(blockCid),
                filecoinjService,
                ChainGetParentMessages.class);
    }

    @Override
    public Request<?, ChainGetTipSetByHeight> ChainGetTipSetByHeight(Long chainEpoch, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.ChainGetTipSetByHeight",
                Arrays.asList(chainEpoch, tsk),
                filecoinjService,
                ChainGetTipSetByHeight.class);
    }

    @Override
    public Request<?, ChainReadObj> ChainReadObj(Cid cid) {
        return new Request<>(
                "Filecoin.ChainReadObj",
                Arrays.asList(cid),
                filecoinjService,
                ChainReadObj.class);
    }

    @Override
    public Request<?, Response<Void>> ChainDeleteObj(Cid cid) {
        return new Request(
                "Filecoin.ChainDeleteObj",
                Arrays.asList(cid),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ChainHasObj> ChainHasObj(Cid cid) {
        return new Request<>(
                "Filecoin.ChainHasObj",
                Arrays.asList(cid),
                filecoinjService,
                ChainHasObj.class);
    }

    @Override
    public Request<?, Response<Void>> ChainStatObj(Cid objectCid, Cid baseCid) {
        return new Request(
                "Filecoin.ChainStatObj",
                Arrays.asList(objectCid, baseCid),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, Response<Void>> ChainSetHead(TipSetKey tsk) {
        return new Request(
                "Filecoin.ChainSetHead",
                Arrays.asList(tsk),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, Response<TipSet>> ChainGetGenesis() {
        return new Request(
                "Filecoin.ChainGetGenesis",
                Collections.<String>emptyList(),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ChainTipSetWeight> ChainTipSetWeight(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.ChainTipSetWeight",
                Arrays.asList(tsk),
                filecoinjService,
                ChainTipSetWeight.class);
    }

    @Override
    public Request<?, IpldObject> ChainGetNode(String p) {
        return new Request<>(
                "Filecoin.ChainGetNode",
                Arrays.asList(p),
                filecoinjService,
                IpldObject.class);
    }

    @Override
    public Request<?, ChainGetMessage> ChainGetMessage(Cid cid) {
        return new Request<>(
                "Filecoin.ChainGetMessage",
                Arrays.asList(cid),
                filecoinjService,
                ChainGetMessage.class);
    }

    @Override
    public Request<?, ChainGetPath> ChainGetPath(TipSetKey from, TipSetKey to) {
        return new Request<>(
                "Filecoin.ChainGetPath",
                Arrays.asList(from, to),
                filecoinjService,
                ChainGetPath.class);
    }

    /*@Override
    public Request<?, ChainExport> ChainExport(Long chainEpoch, boolean oldmsgskip, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.ChainExport",
                Arrays.asList(chainEpoch, oldmsgskip, tsk),
                filecoinjService,
                ChainExport.class);
    }*/

    @Override
    public Request<?, BeaconGetEntry> BeaconGetEntry(Long epoch) {
        return new Request<>(
                "Filecoin.BeaconGetEntry",
                Arrays.asList(epoch),
                filecoinjService,
                BeaconGetEntry.class);
    }

    @Override
    public Request<?, BigintResp> GasEstimateFeeCap(Message message, Long maxqueueblks, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.GasEstimateFeeCap",
                Arrays.asList(message, maxqueueblks, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, GasEstimateGasLimit> GasEstimateGasLimit(Message message, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.GasEstimateGasLimit",
                Arrays.asList(message, tsk),
                filecoinjService,
                GasEstimateGasLimit.class);
    }

    @Override
    public Request<?, BigintResp> GasEstimateGasPremium(Long nblocksincl, String sender, Long gaslimit, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.GasEstimateGasPremium",
                Arrays.asList(nblocksincl, sender, gaslimit, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, GasEstimateMessageGas> GasEstimateMessageGas(Message message, MessageSendSpec messageSendSpec, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.GasEstimateMessageGas",
                Arrays.asList(message, messageSendSpec, tsk),
                filecoinjService,
                GasEstimateMessageGas.class);
    }

    @Override
    public Request<?, SyncState> SyncState() {
        return new Request<>(
                "Filecoin.SyncState",
                Collections.<String>emptyList(),
                filecoinjService,
                SyncState.class);
    }

    @Override
    public Request<?, MpoolPending> MpoolPending(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.MpoolPending",
                Arrays.asList(tsk),
                filecoinjService,
                MpoolPending.class);
    }

    @Override
    public Request<?, MpoolSelect> MpoolSelect(TipSetKey tsk, double tq) {
        return new Request<>(
                "Filecoin.MpoolSelect",
                Arrays.asList(tsk, tq),
                filecoinjService,
                MpoolSelect.class);
    }

    @Override
    public Request<?, MpoolPush> MpoolPush(SignedMessage message) {
        return new Request<>(
                "Filecoin.MpoolPush",
                Arrays.asList(message),
                filecoinjService,
                MpoolPush.class);
    }

    @Override
    public Request<?, MpoolPushUntrusted> MpoolPushUntrusted(SignedMessage message) {
        return new Request<>(
                "Filecoin.MpoolPushUntrusted",
                Arrays.asList(message),
                filecoinjService,
                MpoolPushUntrusted.class);
    }

    @Override
    public Request<?, MpoolPushMessage> MpoolPushMessage(Message message, MessageSendSpec spec) {
        return new Request<>(
                "Filecoin.MpoolPushMessage",
                Arrays.asList(message, spec),
                filecoinjService,
                MpoolPushMessage.class);
    }

    @Override
    public Request<?, MpoolBatchPush> MpoolBatchPush(List<SignedMessage> messages) {
        return new Request<>(
                "Filecoin.MpoolBatchPush",
                Arrays.asList(messages),
                filecoinjService,
                MpoolBatchPush.class);
    }

    @Override
    public Request<?, MpoolBatchPushUntrusted> MpoolBatchPushUntrusted(List<SignedMessage> messages) {
        return new Request<>(
                "Filecoin.MpoolBatchPushUntrusted",
                Arrays.asList(messages),
                filecoinjService,
                MpoolBatchPushUntrusted.class);
    }

    @Override
    public Request<?, MpoolBatchPushMessage> MpoolBatchPushMessage(List<Message> messages, MessageSendSpec spec) {
        return new Request<>(
                "Filecoin.MpoolBatchPushMessage",
                Arrays.asList(messages, spec),
                filecoinjService,
                MpoolBatchPushMessage.class);
    }

    @Override
    public Request<?, MessageCheckStatus> MpoolCheckMessages(List<MessagePrototype> messages) {
        return new Request<>(
                "Filecoin.MpoolCheckMessages",
                Arrays.asList(messages),
                filecoinjService,
                MessageCheckStatus.class);
    }

    @Override
    public Request<?, MessageCheckStatus> MpoolCheckPendingMessages(String address) {
        return new Request<>(
                "Filecoin.MpoolCheckPendingMessages",
                Arrays.asList(address),
                filecoinjService,
                MessageCheckStatus.class);
    }

    @Override
    public Request<?, MessageCheckStatus> MpoolCheckReplaceMessages(List<Message> messages) {
        return new Request<>(
                "Filecoin.MpoolCheckReplaceMessages",
                Arrays.asList(messages),
                filecoinjService,
                MessageCheckStatus.class);
    }

    @Override
    public Request<?, MpoolGetNonce> MpoolGetNonce(String address) {
        return new Request<>(
                "Filecoin.MpoolGetNonce",
                Arrays.asList(address),
                filecoinjService,
                MpoolGetNonce.class);
    }

    /*@Override
    public Request<?, Response<Void>> MpoolSub() {
        return new Request(
                "Filecoin.MpoolSub",
                Collections.emptyList(),
                filecoinjService,
                Response.class);
    }*/

    @Override
    public Request<?, Response<Void>> MpoolClear(Boolean flag) {
        return new Request(
                "Filecoin.MpoolClear",
                Arrays.asList(flag),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, MpoolConfig> MpoolGetConfig() {
        return new Request<>(
                "Filecoin.MpoolGetConfig",
                Collections.emptyList(),
                filecoinjService,
                MpoolConfig.class);
    }

    @Override
    public Request<?, Response<Void>> MpoolSetConfig(MpoolConfig.Config config) {
        return new Request(
                "Filecoin.MpoolSetConfig",
                Arrays.asList(config),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, MinerGetBaseInfo> MinerGetBaseInfo(String address, long chainEpoch, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.MinerGetBaseInfo",
                Arrays.asList(address, chainEpoch, tsk),
                filecoinjService,
                MinerGetBaseInfo.class);
    }

    @Override
    public Request<?, WalletNew> WalletNew(String keyType) {
        return new Request<>(
                "Filecoin.WalletNew",
                Arrays.asList(keyType),
                filecoinjService,
                WalletNew.class);
    }

    @Override
    public Request<?, WalletHas> WalletHas(String address) {
        return new Request<>(
                "Filecoin.WalletHas",
                Arrays.asList(address),
                filecoinjService,
                WalletHas.class);
    }

    @Override
    public Request<?, WalletList> WalletList() {
        return new Request<>(
                "Filecoin.WalletList",
                Collections.<String>emptyList(),
                filecoinjService,
                WalletList.class);
    }

    @Override
    public Request<?, BigintResp> WalletBalance(String address) {
        return new Request<>(
                "Filecoin.WalletBalance",
                Arrays.asList(address),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, WalletSign> WalletSign(String address, String data) {
        return new Request<>(
                "Filecoin.WalletSign",
                Arrays.asList(address, data),
                filecoinjService,
                WalletSign.class);
    }

    @Override
    public Request<?, WalletSignMessage> WalletSignMessage(String address, Message message) {
        return new Request<>(
                "Filecoin.WalletSignMessage",
                Arrays.asList(address, message),
                filecoinjService,
                WalletSignMessage.class);
    }

    @Override
    public Request<?, WalletVerify> WalletVerify(String address, String data, Signature signature) {
        return new Request<>(
                "Filecoin.WalletVerify",
                Arrays.asList(address, data, signature),
                filecoinjService,
                WalletVerify.class);
    }

    @Override
    public Request<?, WalletDefaultAddress> WalletDefaultAddress() {
        return new Request<>(
                "Filecoin.WalletDefaultAddress",
                Collections.emptyList(),
                filecoinjService,
                WalletDefaultAddress.class);
    }

    @Override
    public Request<?, Response<Void>> WalletSetDefault(String address) {
        return new Request(
                "Filecoin.WalletSetDefault",
                Arrays.asList(address),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, WalletExport> WalletExport(String address) {
        return new Request<>(
                "Filecoin.WalletExport",
                Arrays.asList(address),
                filecoinjService,
                WalletExport.class);
    }

    @Override
    public Request<?, WalletImport> WalletImport(KeyInfo keyInfo) {
        return new Request<>(
                "Filecoin.WalletImport",
                Arrays.asList(keyInfo),
                filecoinjService,
                WalletImport.class);
    }

    @Override
    public Request<?, Response<Void>> WalletDelete(String address) {
        return new Request(
                "Filecoin.WalletDelete",
                Arrays.asList(address),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, WalletValidateAddress> WalletValidateAddress(String address) {
        return new Request<>(
                "Filecoin.WalletValidateAddress",
                Arrays.asList(address),
                filecoinjService,
                WalletValidateAddress.class);
    }

    @Override
    public Request<?, ClientImport> ClientImport(FileRef fileRef) {
        return new Request<>(
                "Filecoin.ClientImport",
                Arrays.asList(fileRef),
                filecoinjService,
                ClientImport.class);
    }

    @Override
    public Request<?, Response<Void>> ClientRemoveImport(Long importID) {
        return new Request(
                "Filecoin.ClientRemoveImport",
                Arrays.asList(importID),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ClientStartDeal> ClientStartDeal(StartDealParams params) {
        return new Request<>(
                "Filecoin.ClientStartDeal",
                Arrays.asList(params),
                filecoinjService,
                ClientStartDeal.class);
    }

    @Override
    public Request<?, ClientStatelessDeal> ClientStatelessDeal(StartDealParams params) {
        return new Request<>(
                "Filecoin.ClientStatelessDeal",
                Arrays.asList(params),
                filecoinjService,
                ClientStatelessDeal.class);
    }

    @Override
    public Request<?, ClientGetDealInfo> ClientGetDealInfo(Cid cid) {
        return new Request<>(
                "Filecoin.ClientGetDealInfo",
                Arrays.asList(cid),
                filecoinjService,
                ClientGetDealInfo.class);
    }

    @Override
    public Request<?, ClientListDeals> ClientListDeals() {
        return new Request<>(
                "Filecoin.ClientListDeals",
                Collections.emptyList(),
                filecoinjService,
                ClientListDeals.class);
    }

    /*@Override
    public Request<?, Response> ClientGetDealUpdates() {
        return new Request<>(
                "Filecoin.ClientGetDealUpdates",
                Collections.emptyList(),
                filecoinjService,
                Response.class);
    }*/

    @Override
    public Request<?, ClientGetDealStatus> ClientGetDealStatus(Long statusCode) {
        return new Request<>(
                "Filecoin.ClientGetDealStatus",
                Arrays.asList(statusCode),
                filecoinjService,
                ClientGetDealStatus.class);
    }

    @Override
    public Request<?, ClientHasLocal> ClientHasLocal(Cid root) {
        return new Request<>(
                "Filecoin.ClientHasLocal",
                Arrays.asList(root),
                filecoinjService,
                ClientHasLocal.class);
    }

    @Override
    public Request<?, ClientFindData> ClientFindData(Cid root, Cid piece) {
        return new Request<>(
                "Filecoin.ClientFindData",
                Arrays.asList(root, piece),
                filecoinjService,
                ClientFindData.class);
    }

    @Override
    public Request<?, ClientMinerQueryOffer> ClientMinerQueryOffer(String miner, Cid root, Cid piece) {
        return new Request<>(
                "Filecoin.ClientMinerQueryOffer",
                Arrays.asList(miner, root, piece),
                filecoinjService,
                ClientMinerQueryOffer.class);
    }

    @Override
    public Request<?, Response<Void>> ClientRetrieve(RetrievalOrder order, FileRef ref) {
        return new Request(
                "Filecoin.ClientRetrieve",
                Arrays.asList(order, ref),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ClientRetrievalInfo> ClientListRetrievals() {
        return new Request<>(
                "Filecoin.ClientListRetrievals",
                Collections.emptyList(),
                filecoinjService,
                ClientRetrievalInfo.class);
    }

    /*@Override
    public Request<?, ClientRetrievalInfo> ClientGetRetrievalUpdates() {
        return new Request<>(
                "Filecoin.ClientGetRetrievalUpdates",
                Collections.emptyList(),
                filecoinjService,
                ClientRetrievalInfo.class);
    }*/

    @Override
    public Request<?, ClientQueryAsk> ClientQueryAsk(String peer, String miner) {
        return new Request<>(
                "Filecoin.ClientQueryAsk",
                Arrays.asList(peer, miner),
                filecoinjService,
                ClientQueryAsk.class);
    }

    @Override
    public Request<?, ClientDealPieceCID> ClientDealPieceCID(Cid root) {
        return new Request<>(
                "Filecoin.ClientDealPieceCID",
                Arrays.asList(root),
                filecoinjService,
                ClientDealPieceCID.class);
    }

    @Override
    public Request<?, ClientCalcCommP> ClientCalcCommP(String inpath) {
        return new Request<>(
                "Filecoin.ClientCalcCommP",
                Arrays.asList(inpath),
                filecoinjService,
                ClientCalcCommP.class);
    }

    @Override
    public Request<?, Response<Void>> ClientGenCar(FileRef ref, String outpath) {
        return new Request(
                "Filecoin.ClientGenCar",
                Arrays.asList(ref, outpath),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ClientDealSize> ClientDealSize(Cid root) {
        return new Request<>(
                "Filecoin.ClientDealSize",
                Arrays.asList(root),
                filecoinjService,
                ClientDealSize.class);
    }

    @Override
    public Request<?, ClientListDataTransfers> ClientListDataTransfers() {
        return new Request<>(
                "Filecoin.ClientListDataTransfers",
                Collections.emptyList(),
                filecoinjService,
                ClientListDataTransfers.class);
    }

    /*@Override
    public Request<?, ClientDataTransferUpdates> ClientDataTransferUpdates() {
        return new Request<>(
                "Filecoin.ClientDataTransferUpdates",
                Collections.emptyList(),
                filecoinjService,
                ClientDataTransferUpdates.class);
    }*/

    @Override
    public Request<?, Response<Void>> ClientRestartDataTransfer(Long transferID, String otherPeer, Boolean isInitiator) {
        return new Request(
                "Filecoin.ClientRestartDataTransfer",
                Arrays.asList(transferID, otherPeer, isInitiator),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, Response<Void>> ClientCancelDataTransfer(Long transferID, String otherPeer, Boolean isInitiator) {
        return new Request(
                "Filecoin.ClientCancelDataTransfer",
                Arrays.asList(transferID, otherPeer, isInitiator),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, Response<Void>> ClientRetrieveTryRestartInsufficientFunds(String paymentChannel) {
        return new Request(
                "Filecoin.ClientRetrieveTryRestartInsufficientFunds",
                Arrays.asList(paymentChannel),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, Response<Void>> ClientCancelRetrievalDeal(Long dealid) {
        return new Request(
                "Filecoin.ClientCancelRetrievalDeal",
                Arrays.asList(dealid),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, ClientListImports> ClientListImports() {
        return new Request<>(
                "Filecoin.ClientListImports",
                Collections.emptyList(),
                filecoinjService,
                ClientListImports.class);
    }

    @Override
    public Request<?, StateCall> StateCall(Message message, TipSetKey tipSetKey) {
        return new Request<>(
                "Filecoin.StateCall",
                Arrays.asList(message, tipSetKey),
                filecoinjService,
                StateCall.class);
    }

    @Override
    public Request<?, StateReplay> StateReplay(TipSetKey tipSetKey, Cid cid) {
        return new Request<>(
                "Filecoin.StateReplay",
                Arrays.asList(tipSetKey, cid),
                filecoinjService,
                StateReplay.class);
    }

    @Override
    public Request<?, StateGetActor> StateGetActor(String actor, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateGetActor",
                Arrays.asList(actor, tsk),
                filecoinjService,
                StateGetActor.class);
    }

    @Override
    public Request<?, StateReadState> StateReadState(String actor, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateReadState",
                Arrays.asList(actor, tsk),
                filecoinjService,
                StateReadState.class);
    }

    @Override
    public Request<?, StateListMessages> StateListMessages(MessageMatch match, TipSetKey tsk, Long toht) {
        return new Request<>(
                "Filecoin.StateListMessages",
                Arrays.asList(match, tsk, toht),
                filecoinjService,
                StateListMessages.class);
    }

    @Override
    public Request<?, Response<Void>> StateDecodeParams(String toAddr, Long method, String params, TipSetKey tsk) {
        return new Request(
                "Filecoin.StateDecodeParams",
                Arrays.asList(toAddr, method, params, tsk),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, StateNetworkName> StateNetworkName() {
        return new Request<>(
                "Filecoin.StateNetworkName",
                Collections.emptyList(),
                filecoinjService,
                StateNetworkName.class);
    }

    @Override
    public Request<?, StateMinerActiveSectors> StateMinerSectors(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerSectors",
                Arrays.asList(address, Arrays.asList(0), tsk),
                filecoinjService,
                StateMinerActiveSectors.class);
    }

    @Override
    public Request<?, StateMinerActiveSectors> StateMinerActiveSectors(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerActiveSectors",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerActiveSectors.class);
    }

    @Override
    public Request<?, StateMinerProvingDeadline> StateMinerProvingDeadline(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerProvingDeadline",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerProvingDeadline.class);
    }

    @Override
    public Request<?, StateMinerPower> StateMinerPower(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerPower",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerPower.class);
    }

    @Override
    public Request<?, StateMinerInfo> StateMinerInfo(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerInfo",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerInfo.class);
    }

    @Override
    public Request<?, StateMinerDeadlines> StateMinerDeadlines(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerDeadlines",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerDeadlines.class);
    }

    @Override
    public Request<?, StateMinerPartitions> StateMinerPartitions(String address, Long dlIdx, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerPartitions",
                Arrays.asList(address, dlIdx, tsk),
                filecoinjService,
                StateMinerPartitions.class);
    }

    @Override
    public Request<?, StateAllMinerFaults> StateAllMinerFaults(Long lookback, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateAllMinerFaults",
                Arrays.asList(lookback, tsk),
                filecoinjService,
                StateAllMinerFaults.class);
    }

    @Override
    public Request<?, BigintResp> StateMinerPreCommitDepositForPower(String address, SectorPreCommitInfo miner, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerPreCommitDepositForPower",
                Arrays.asList(address, miner, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, BigintResp> StateMinerInitialPledgeCollateral(String address, SectorPreCommitInfo miner, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerInitialPledgeCollateral",
                Arrays.asList(address, miner, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, BigintResp> StateMinerAvailableBalance(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerAvailableBalance",
                Arrays.asList(address, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, StateMinerSectorAllocated> StateMinerSectorAllocated(String address, Long sectorNumber, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerSectorAllocated",
                Arrays.asList(address, sectorNumber, tsk),
                filecoinjService,
                StateMinerSectorAllocated.class);
    }

    @Override
    public Request<?, StateSectorPreCommitInfo> StateSectorPreCommitInfo(String address, Long sectorNumber, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateSectorPreCommitInfo",
                Arrays.asList(address, sectorNumber, tsk),
                filecoinjService,
                StateSectorPreCommitInfo.class);
    }

    @Override
    public Request<?, StateSectorGetInfo> StateSectorGetInfo(String address, Long sectorNumber, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateSectorGetInfo",
                Arrays.asList(address, sectorNumber, tsk),
                filecoinjService,
                StateSectorGetInfo.class);
    }

    @Override
    public Request<?, StateSectorExpiration> StateSectorExpiration(String address, Long sectorNumber, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateSectorExpiration",
                Arrays.asList(address, sectorNumber, tsk),
                filecoinjService,
                StateSectorExpiration.class);
    }

    @Override
    public Request<?, StateSectorPartition> StateSectorPartition(String address, Long sectorNumber, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateSectorPartition",
                Arrays.asList(address, sectorNumber, tsk),
                filecoinjService,
                StateSectorPartition.class);
    }

    @Override
    public Request<?, StateSearchMsg> StateSearchMsg(Cid cid) {
        return new Request<>(
                "Filecoin.StateSearchMsg",
                Arrays.asList(cid),
                filecoinjService,
                StateSearchMsg.class);
    }

    @Override
    public Request<?, StateSearchMsg> StateSearchMsgLimited(Cid msg, Long limit) {
        return new Request<>(
                "Filecoin.StateSearchMsgLimited",
                Arrays.asList(msg, limit),
                filecoinjService,
                StateSearchMsg.class);
    }

    @Override
    public Request<?, StateWaitMsg> StateWaitMsg(Cid cid, Long confidence) {
        return new Request<>(
                "Filecoin.StateWaitMsg",
                Arrays.asList(cid, confidence),
                filecoinjService,
                StateWaitMsg.class);
    }

    @Override
    public Request<?, StateListMiners> StateListMiners(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateListMiners",
                Arrays.asList(tsk),
                filecoinjService,
                StateListMiners.class);
    }

    @Override
    public Request<?, StateListActors> StateListActors(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateListActors",
                Arrays.asList(tsk),
                filecoinjService,
                StateListActors.class);
    }

    @Override
    public Request<?, StateMarketBalance> StateMarketBalance(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMarketBalance",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMarketBalance.class);
    }

    @Override
    public Request<?, StateMarketParticipants> StateMarketParticipants(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMarketParticipants",
                Arrays.asList(tsk),
                filecoinjService,
                StateMarketParticipants.class);
    }

    @Override
    public Request<?, StateMarketDeals> StateMarketDeals(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMarketDeals",
                Arrays.asList(tsk),
                filecoinjService,
                StateMarketDeals.class);
    }

    @Override
    public Request<?, StateMarketStorageDeal> StateMarketStorageDeal(Long dealID, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMarketStorageDeal",
                Arrays.asList(dealID, tsk),
                filecoinjService,
                StateMarketStorageDeal.class);
    }

    @Override
    public Request<?, StateLookupID> StateLookupID(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateLookupID",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateLookupID.class);
    }

    @Override
    public Request<?, StateAccountKey> StateAccountKey(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateAccountKey",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateAccountKey.class);
    }

    @Override
    public Request<?, StateMinerSectorCount> StateMinerSectorCount(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateMinerSectorCount",
                Arrays.asList(address, tsk),
                filecoinjService,
                StateMinerSectorCount.class);
    }

    @Override
    public Request<?, StateCompute> StateCompute(Long chainEpoch, List<Message> msg, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateCompute",
                Arrays.asList(chainEpoch, msg, tsk),
                filecoinjService,
                StateCompute.class);
    }

    @Override
    public Request<?, BigintResp> StateVerifierStatus(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateVerifierStatus",
                Arrays.asList(address, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, BigintResp> StateVerifiedClientStatus(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateVerifiedClientStatus",
                Arrays.asList(address, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, StateVerifiedRegistryRootKey> StateVerifiedRegistryRootKey(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateVerifiedRegistryRootKey",
                Arrays.asList(tsk),
                filecoinjService,
                StateVerifiedRegistryRootKey.class);
    }

    @Override
    public Request<?, StateDealProviderCollateralBounds> StateDealProviderCollateralBounds(Long paddedPieceSize, Boolean bool, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateDealProviderCollateralBounds",
                Arrays.asList(paddedPieceSize, bool, tsk),
                filecoinjService,
                StateDealProviderCollateralBounds.class);
    }

    @Override
    public Request<?, BigintResp> StateCirculatingSupply(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateCirculatingSupply",
                Arrays.asList(tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, StateVMCirculatingSupplyInternal> StateVMCirculatingSupplyInternal(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateVMCirculatingSupplyInternal",
                Arrays.asList(tsk),
                filecoinjService,
                StateVMCirculatingSupplyInternal.class);
    }

    @Override
    public Request<?, StateNetworkVersion> StateNetworkVersion(TipSetKey tsk) {
        return new Request<>(
                "Filecoin.StateNetworkVersion",
                Arrays.asList(tsk),
                filecoinjService,
                StateNetworkVersion.class);
    }

    @Override
    public Request<?, BigintResp> MsigGetAvailableBalance(String multiAddress, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.MsigGetAvailableBalance",
                Arrays.asList(multiAddress, tsk),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, MsigGetVestingSchedule> MsigGetVestingSchedule(String multiAddress, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.MsigGetVestingSchedule",
                Arrays.asList(multiAddress, tsk),
                filecoinjService,
                MsigGetVestingSchedule.class);
    }

    @Override
    public Request<?, BigintResp> MsigGetVested(String multiAddress, TipSetKey start, TipSetKey end) {
        return new Request<>(
                "Filecoin.MsigGetVested",
                Arrays.asList(multiAddress, start, end),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, MsigGetPending> MsigGetPending(String address, TipSetKey tsk) {
        return new Request<>(
                "Filecoin.MsigGetPending",
                Arrays.asList(address, tsk),
                filecoinjService,
                MsigGetPending.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigCreate(Long required, List<String> signers, Long chainEpoch, BigInteger initialBalance, String sender, BigInteger gasPrice) {
        return new Request<>(
                "Filecoin.MsigCreate",
                Arrays.asList(required, signers, chainEpoch, initialBalance.toString(), sender, gasPrice.toString()),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigPropose(String multiAddress, String recipient, BigInteger value, String sender, Integer method, String params) {
        return new Request<>(
                "Filecoin.MsigPropose",
                Arrays.asList(multiAddress, recipient, value.toString(), sender, method, params),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigApprove(String multiAddress, Long proposedId, String signer) {
        return new Request<>(
                "Filecoin.MsigApprove",
                Arrays.asList(multiAddress, proposedId, signer),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigApproveTxnHash(String multiAddress, Long proposedId, String proposer, String recipient, BigInteger value, String sender, Integer method, String params) {
        return new Request<>(
                "Filecoin.MsigApproveTxnHash",
                Arrays.asList(multiAddress, proposedId, proposer, recipient, value.toString(), sender, method, params),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigCancel(String multiAddress, Long proposedId, String recipient, BigInteger value, String sender, Integer method, String params) {
        return new Request<>(
                "Filecoin.MsigCancel",
                Arrays.asList(multiAddress, proposedId, recipient, value.toString(), sender, method, params),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigAddPropose(String multiAddress, String sender, String newSigner, Boolean increased) {
        return new Request<>(
                "Filecoin.MsigAddPropose",
                Arrays.asList(multiAddress, sender, newSigner, increased),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigAddApprove(String multiAddress, String sender, Long proposedID, String proposer, String newSigner, Boolean increased) {
        return new Request<>(
                "Filecoin.MsigAddApprove",
                Arrays.asList(multiAddress, sender, proposedID, proposer, newSigner, increased),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigAddCancel(String multiAddress, String sender, Long proposedID, String newSigner, Boolean increased) {
        return new Request<>(
                "Filecoin.MsigAddCancel",
                Arrays.asList(multiAddress, sender, proposedID, newSigner, increased),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigSwapPropose(String multiAddress, String sender, String oldSigner, String newSigner) {
        return new Request<>(
                "Filecoin.MsigSwapPropose",
                Arrays.asList(multiAddress, sender, oldSigner, newSigner),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigSwapApprove(String multiAddress, String sender, Long proposedID, String proposer, String oldSigner, String newSigner) {
        return new Request<>(
                "Filecoin.MsigSwapApprove",
                Arrays.asList(multiAddress, sender, proposedID, proposer, oldSigner, newSigner),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigSwapCancel(String multiAddress, String sender, Long proposedID, String oldSigner, String newSigner) {
        return new Request<>(
                "Filecoin.MsigSwapCancel",
                Arrays.asList(multiAddress, sender, proposedID, oldSigner, newSigner),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, MultiMessagePrototype> MsigRemoveSigner(String multiAddress, String proposer, String toRemove, Boolean decrease) {
        return new Request<>(
                "Filecoin.MsigRemoveSigner",
                Arrays.asList(multiAddress, proposer, toRemove, decrease),
                filecoinjService,
                MultiMessagePrototype.class);
    }

    @Override
    public Request<?, BigintResp> MarketGetReserved(String address) {
        return new Request<>(
                "Filecoin.MarketGetReserved",
                Arrays.asList(address),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, MarketReserveFunds> MarketReserveFunds(String wallet, String address, BigInteger amt) {
        return new Request<>(
                "Filecoin.MarketReserveFunds",
                Arrays.asList(wallet, address, amt.toString()),
                filecoinjService,
                MarketReserveFunds.class);
    }

    @Override
    public Request<?, Response<Void>> MarketReleaseFunds(String address, BigInteger amt) {
        return new Request(
                "Filecoin.MarketReleaseFunds",
                Arrays.asList(address, amt.toString()),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, MarketWithdraw> MarketWithdraw(String wallet, String address, BigInteger amt) {
        return new Request<>(
                "Filecoin.MarketWithdraw",
                Arrays.asList(wallet, address, amt.toString()),
                filecoinjService,
                MarketWithdraw.class);
    }

    @Override
    public Request<?, PaychGet> PaychGet(String from, String to, BigInteger amt) {
        return new Request<>(
                "Filecoin.PaychGet",
                Arrays.asList(from, to, amt.toString()),
                filecoinjService,
                PaychGet.class);
    }

    @Override
    public Request<?, PaychGetWaitReady> PaychGetWaitReady(Cid cid) {
        return new Request<>(
                "Filecoin.PaychGetWaitReady",
                Arrays.asList(cid),
                filecoinjService,
                PaychGetWaitReady.class);
    }

    @Override
    public Request<?, PaychAvailableFunds> PaychAvailableFunds(String address) {
        return new Request<>(
                "Filecoin.PaychAvailableFunds",
                Arrays.asList(address),
                filecoinjService,
                PaychAvailableFunds.class);
    }

    @Override
    public Request<?, PaychAvailableFunds> PaychAvailableFundsByFromTo(String from, String to) {
        return new Request<>(
                "Filecoin.PaychAvailableFundsByFromTo",
                Arrays.asList(from, to),
                filecoinjService,
                PaychAvailableFunds.class);
    }

    @Override
    public Request<?, PaychList> PaychList() {
        return new Request<>(
                "Filecoin.PaychList",
                Collections.emptyList(),
                filecoinjService,
                PaychList.class);
    }

    @Override
    public Request<?, PaychStatus> PaychStatus(String address) {
        return new Request<>(
                "Filecoin.PaychStatus",
                Arrays.asList(address),
                filecoinjService,
                PaychStatus.class);
    }

    @Override
    public Request<?, PaychSettle> PaychSettle(String address) {
        return new Request<>(
                "Filecoin.PaychSettle",
                Arrays.asList(address),
                filecoinjService,
                PaychSettle.class);
    }

    @Override
    public Request<?, PaychCollect> PaychCollect(String address) {
        return new Request<>(
                "Filecoin.PaychCollect",
                Arrays.asList(address),
                filecoinjService,
                PaychCollect.class);
    }

    @Override
    public Request<?, PaychAllocateLane> PaychAllocateLane(String address) {
        return new Request<>(
                "Filecoin.PaychAllocateLane",
                Arrays.asList(address),
                filecoinjService,
                PaychAllocateLane.class);
    }

    @Override
    public Request<?, PaychNewPayment> PaychNewPayment(String from, String to, List<VoucherSpec> vouchers) {
        return new Request<>(
                "Filecoin.PaychNewPayment",
                Arrays.asList(from, to, vouchers),
                filecoinjService,
                PaychNewPayment.class);
    }

    @Override
    public Request<?, Response<Void>> PaychVoucherCheckValid(String address, SignedVoucher paych) {
        return new Request(
                "Filecoin.PaychVoucherCheckValid",
                Arrays.asList(address, paych),
                filecoinjService,
                Response.class);
    }

    @Override
    public Request<?, PaychVoucherCheckSpendable> PaychVoucherCheckSpendable(String address, SignedVoucher paych, String secret, String proof) {
        return new Request<>(
                "Filecoin.PaychVoucherCheckSpendable",
                Arrays.asList(address, paych, secret, proof),
                filecoinjService,
                PaychVoucherCheckSpendable.class);
    }

    @Override
    public Request<?, PaychVoucherCreate> PaychVoucherCreate(String address, BigInteger amount, Long lane) {
        return new Request<>(
                "Filecoin.PaychVoucherCreate",
                Arrays.asList(address, amount.toString(), lane),
                filecoinjService,
                PaychVoucherCreate.class);
    }

    @Override
    public Request<?, BigintResp> PaychVoucherAdd(String address, SignedVoucher paych, String params, BigInteger amt) {
        return new Request<>(
                "Filecoin.PaychVoucherAdd",
                Arrays.asList(address, paych, params, amt.toString()),
                filecoinjService,
                BigintResp.class);
    }

    @Override
    public Request<?, PaychVoucherList> PaychVoucherList(String address) {
        return new Request<>(
                "Filecoin.PaychVoucherList",
                Arrays.asList(address),
                filecoinjService,
                PaychVoucherList.class);
    }

    @Override
    public Request<?, PaychVoucherSubmit> PaychVoucherSubmit(String address, SignedVoucher paych, String secret, String proof) {
        return new Request<>(
                "Filecoin.PaychVoucherSubmit",
                Arrays.asList(address, paych, secret, proof),
                filecoinjService,
                PaychVoucherSubmit.class);
    }

    @Override
    public Request<?, NodeStatus> NodeStatus(Boolean inclChainStatus) {
        return new Request<>(
                "Filecoin.NodeStatus",
                Arrays.asList(inclChainStatus),
                filecoinjService,
                NodeStatus.class);
    }

    @Override
    public Request<?, Response<Void>> CreateBackup(String fpath) {
        return new Request(
                "Filecoin.CreateBackup",
                Arrays.asList(),
                filecoinjService,
                Response.class);
    }


    @Override
    public void shutdown() {
        try {
            filecoinjService.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to close web3j service", e);
        }
    }
}
