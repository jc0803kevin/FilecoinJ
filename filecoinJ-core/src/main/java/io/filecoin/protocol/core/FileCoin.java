package io.filecoin.protocol.core;

import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import io.filecoin.crypto.types.SignedMessage;
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

import java.math.BigInteger;
import java.util.List;

public interface FileCoin {

    /**
     * returns peerID of libp2p node backing this API
     *
     * @return
     */
    Request<?, ID> id();

    /**
     * provides information about API provider
     *
     * @return
     */
    Request<?, FilecoinClientVersion> getVersion();

    /**
     * returns the current head of the chain
     *
     * @return
     */
    Request<?, ChainHead> chainHead();

    /**
     * returns the block specified by the given CID
     *
     * @param cid
     * @return
     */
    Request<?, ChainGetBlock> ChainGetBlock(Cid cid);

    /**
     * returns messages stored in the specified block
     *
     * @param blockCid
     * @return
     */
    Request<?, ChainGetBlockMessages> ChainGetBlockMessages(Cid blockCid); 

    /**
     * returns receipts for messages in parent tipset of
     *
     * @param blockCid
     * @return
     */
    Request<?, ChainGetParentReceipts> ChainGetParentReceipts(Cid blockCid); 

    /**
     * returns messages stored in parent tipset of the
     *
     * @param blockCid specified block
     * @return
     */
    Request<?, ChainGetParentMessages> ChainGetParentMessages(Cid blockCid); 

    /**
     * looks back for a tipset at the specified epoch.
     *
     * @param chainEpoch
     * @param tsk
     * @return
     */
    Request<?, ChainGetTipSetByHeight> ChainGetTipSetByHeight(Long chainEpoch, TipSetKey tsk); 

    /**
     * reads ipld nodes referenced by the specified CID from chain
     *
     * @param cid
     * @return
     */
    Request<?, ChainReadObj> ChainReadObj(Cid cid); 

    /**
     * deletes node referenced by the given CID
     *
     * @param cid
     * @return
     */
    Request<?, Response<Void>> ChainDeleteObj(Cid cid);

    /**
     * checks if a given CID exists in the chain blockstore.
     *
     * @param cid
     * @return
     */
    Request<?, ChainHasObj> ChainHasObj(Cid cid);

    /**
     * returns statistics about the graph referenced by 'obj'.
     * If 'base' is also specified, then the returned stat will be a diff
     * between the two objects.
     *
     * @param objectCid
     * @param baseCid
     * @return
     */
    Request<?, Response<Void>> ChainStatObj(Cid objectCid, Cid baseCid);

    /**
     * forcefully sets current chain head. Use with caution.
     *
     * @param tsk
     * @return
     */
    Request<?, Response<Void>> ChainSetHead(TipSetKey tsk);

    /**
     * returns the genesis tipset.
     *
     * @return
     */
    Request<?, Response<TipSet>> ChainGetGenesis(); 

    /**
     * computes weight for the specified tipset.
     *
     * @param tsk
     * @return
     */
    Request<?, ChainTipSetWeight> ChainTipSetWeight(TipSetKey tsk); 

    Request<?, IpldObject> ChainGetNode(String p);

    /**
     * reads a message referenced by the specified CID from the chain blockstore.
     *
     * @param cid
     * @return
     */
    Request<?, ChainGetMessage> ChainGetMessage(Cid cid); 

    /**
     * ChainGetPath returns a set of revert/apply operations needed to get from
     * one tipset to another, for example:
     * ```
     * to
     * ^
     * from   tAA
     * ^     ^
     * tBA    tAB
     * ^---*--^
     * ^
     * tRR
     * ```
     * Would return `[revert(tBA), apply(tAB), apply(tAA)]`
     *
     * @param from
     * @param to
     * @return
     */
    Request<?, ChainGetPath> ChainGetPath(TipSetKey from, TipSetKey to);

    /**
     * returns the beacon entry for the given filecoin epoch. If
     * the entry has not yet been produced, the call will block until the entry
     * becomes available
     *
     * @param epoch
     * @return
     */
    Request<?, BeaconGetEntry> BeaconGetEntry(Long epoch);

    /**
     * estimates gas fee cap
     *
     * @param message
     * @param maxqueueblks
     * @param tsk
     * @return
     */
    Request<?, BigintResp> GasEstimateFeeCap(Message message, Long maxqueueblks, TipSetKey tsk);

    /**
     * estimates gas used by the message and returns it. It fails if message fails to execute.
     *
     * @param message
     * @param tsk
     * @return
     */
    Request<?, GasEstimateGasLimit> GasEstimateGasLimit(Message message, TipSetKey tsk);

    /**
     * estimates what gas price should be used for a
     * message to have high likelihood of inclusion in `nblocksincl` epochs.
     *
     * @param nblocksincl
     * @param sender
     * @param gaslimit
     * @param tsk
     * @return
     */
    Request<?, BigintResp> GasEstimateGasPremium(Long nblocksincl, String sender, Long gaslimit, TipSetKey tsk);

    /**
     * estimates gas values for unset message gas fields
     *
     * @param message
     * @param messageSendSpec
     * @param tsk
     * @return
     */
    Request<?, GasEstimateMessageGas> GasEstimateMessageGas(Message message, MessageSendSpec messageSendSpec, TipSetKey tsk);

    /**
     * returns the current status of the lotus sync system.
     *
     * @return
     */
    Request<?, SyncState> SyncState();

    /**
     * returns pending mempool messages.
     *
     * @param tsk
     * @return
     */
    Request<?, MpoolPending> MpoolPending(TipSetKey tsk);

    /**
     * returns a list of pending messages for inclusion in the next block
     *
     * @param tsk
     * @param tq
     * @return
     */
    Request<?, MpoolSelect> MpoolSelect(TipSetKey tsk, double tq);

    /**
     * pushes a signed message to mempool.
     *
     * @param message
     * @return
     */
    Request<?, MpoolPush> MpoolPush(SignedMessage message);

    /**
     * pushes a signed message to mempool from untrusted sources.
     *
     * @param message
     * @return
     */
    Request<?, MpoolPushUntrusted> MpoolPushUntrusted(SignedMessage message);

    /**
     * atomically assigns a nonce, signs, and pushes a message to mempool.
     * maxFee is only used when GasFeeCap/GasPremium fields aren't specified
     * <p>
     * When maxFee is set to 0, MpoolPushMessage will guess appropriate fee
     * based on current chain conditions
     *
     * @param message
     * @param spec
     * @return
     */
    Request<?, MpoolPushMessage> MpoolPushMessage(Message message, MessageSendSpec spec);

    /**
     * batch pushes a signed message to mempool.
     *
     * @param messages
     * @return
     */
    Request<?, MpoolBatchPush> MpoolBatchPush(List<SignedMessage> messages);

    /**
     * batch pushes a signed message to mempool from untrusted sources.
     *
     * @param messages
     * @return
     */
    Request<?, MpoolBatchPushUntrusted> MpoolBatchPushUntrusted(List<SignedMessage> messages);

    /**
     * batch pushes a unsigned message to mempool.
     *
     * @param messages
     * @param spec
     * @return
     */
    Request<?, MpoolBatchPushMessage> MpoolBatchPushMessage(List<Message> messages, MessageSendSpec spec);

    /**
     * performs logical checks on a batch of messages
     *
     * @param messages
     * @return
     */
    Request<?, MessageCheckStatus> MpoolCheckMessages(List<MessagePrototype> messages);

    /**
     * performs logical checks for all pending messages from a given address
     *
     * @param address
     * @return
     */
    Request<?, MessageCheckStatus> MpoolCheckPendingMessages(String address);

    /**
     * performs logical checks on pending messages with replacement
     *
     * @return
     */
    Request<?, MessageCheckStatus> MpoolCheckReplaceMessages(List<Message> messages);

    /**
     * gets next nonce for the specified sender.
     * Note that this method may not be atomic. Use MpoolPushMessage instead.
     *
     * @param address
     * @return
     */
    Request<?, MpoolGetNonce> MpoolGetNonce(String address);

    /**
     * clears pending messages from the mpool
     *
     * @param flag
     * @return
     */
    Request<?, Response<Void>> MpoolClear(Boolean flag);

    /**
     * returns (a copy of) the current mpool config
     *
     * @return
     */
    Request<?, MpoolConfig> MpoolGetConfig();

    /**
     * sets the mpool config to (a copy of) the supplied config
     *
     * @param config
     * @return
     */
    Request<?, Response<Void>> MpoolSetConfig(MpoolConfig.Config config);

    Request<?, MinerGetBaseInfo> MinerGetBaseInfo(String address, long chainEpoch, TipSetKey tsk);

    /**
     * creates a new address in the wallet with the given sigType.
     * Available key types: bls, secp256k1, secp256k1-ledger
     * Support for numerical types: 1 - secp256k1, 2 - BLS is deprecated
     *
     * @param keyType
     * @return
     */
    Request<?, WalletNew> WalletNew(String keyType);

    /**
     * indicates whether the given address is in the wallet.
     *
     * @param address
     * @return
     */
    Request<?, WalletHas> WalletHas(String address);

    /**
     * lists all the addresses in the wallet.
     *
     * @return
     */
    Request<?, WalletList> WalletList();

    /**
     * returns the balance of the given address at the current head of the chain.
     *
     * @param address
     * @return
     */
    Request<?, BigintResp> WalletBalance(String address);

    /**
     * signs the given bytes using the given address.
     *
     * @param address
     * @param data
     * @return
     */
    Request<?, WalletSign> WalletSign(String address, String data);

    /**
     * signs the given message using the given address.
     *
     * @param address
     * @param message
     * @return
     */
    Request<?, WalletSignMessage> WalletSignMessage(String address, Message message);

    /**
     * takes an address, a signature, and some bytes, and indicates whether the signature is valid.
     * The address does not have to be in the wallet.
     *
     * @param address
     * @param data
     * @param signature
     * @return
     */
    Request<?, WalletVerify> WalletVerify(String address, String data, Signature signature);

    /**
     * returns the address marked as default in the wallet.
     *
     * @return
     */
    Request<?, WalletDefaultAddress> WalletDefaultAddress();

    /**
     * marks the given address as as the default one.
     *
     * @param address
     * @return
     */
    Request<?, Response<Void>> WalletSetDefault(String address);

    /**
     * returns the private key of an address in the wallet.
     *
     * @param address
     * @return
     */
    Request<?, WalletExport> WalletExport(String address);

    /**
     * receives a KeyInfo, which includes a private key, and imports it into the wallet.
     *
     * @param keyInfo
     * @return
     */
    Request<?, WalletImport> WalletImport(KeyInfo keyInfo);

    /**
     * deletes an address from the wallet.
     *
     * @param address
     * @return
     */
    Request<?, Response<Void>> WalletDelete(String address);

    /**
     * validates whether a given string can be decoded as a well-formed address
     *
     * @param address
     * @return
     */
    Request<?, WalletValidateAddress> WalletValidateAddress(String address);

    /**
     * imports file under the specified path into filestore.
     *
     * @param fileRef
     * @return
     */
    Request<?, ClientImport> ClientImport(FileRef fileRef);

    /**
     * removes file import
     *
     * @param importID
     * @return
     */
    Request<?, Response<Void>> ClientRemoveImport(Long importID);

    /**
     * proposes a deal with a miner.
     *
     * @param params
     * @return
     */
    Request<?, ClientStartDeal> ClientStartDeal(StartDealParams params);

    /**
     * fire-and-forget-proposes an offline deal to a miner without subsequent tracking.
     *
     * @param params
     * @return
     */
    Request<?, ClientStatelessDeal> ClientStatelessDeal(StartDealParams params);

    /**
     * returns the latest information about a given deal.
     *
     * @param cid
     * @return
     */
    Request<?, ClientGetDealInfo> ClientGetDealInfo(Cid cid);

    /**
     * returns information about the deals made by the local client.
     *
     * @return
     */
    Request<?, ClientListDeals> ClientListDeals();

    /**
     * returns status given a code
     *
     * @param statusCode
     * @return
     */
    Request<?, ClientGetDealStatus> ClientGetDealStatus(Long statusCode);

    /**
     * indicates whether a certain CID is locally stored.
     *
     * @param root
     * @return
     */
    Request<?, ClientHasLocal> ClientHasLocal(Cid root);

    /**
     * identifies peers that have a certain file, and returns QueryOffers (one per peer).
     *
     * @param root
     * @param piece
     * @return
     */
    Request<?, ClientFindData> ClientFindData(Cid root, Cid piece);

    /**
     * returns a QueryOffer for the specific miner and file.
     *
     * @param miner
     * @param root
     * @param piece
     * @return
     */
    Request<?, ClientMinerQueryOffer> ClientMinerQueryOffer(String miner, Cid root, Cid piece);

    /**
     * initiates the retrieval of a file, as specified in the order.
     *
     * @param order
     * @param ref
     * @return
     */
    Request<?, Response<Void>> ClientRetrieve(RetrievalOrder order, FileRef ref);

    /**
     * returns information about retrievals made by the local client
     *
     * @return
     */
    Request<?, ClientRetrievalInfo> ClientListRetrievals();

    /**
     * returns a signed StorageAsk from the specified miner.
     *
     * @param peer
     * @param miner
     * @return
     */
    Request<?, ClientQueryAsk> ClientQueryAsk(String peer, String miner);

    /**
     * calculates the CommP and data size of the specified CID
     *
     * @param root
     * @return
     */
    Request<?, ClientDealPieceCID> ClientDealPieceCID(Cid root);

    /**
     * calculates the CommP for a specified file
     *
     * @param inpath
     * @return
     */
    Request<?, ClientCalcCommP> ClientCalcCommP(String inpath);

    /**
     * generates a CAR file for the specified file.
     *
     * @param ref
     * @param outpath
     * @return
     */
    Request<?, Response<Void>> ClientGenCar(FileRef ref, String outpath);

    /**
     * calculates real deal data size
     *
     * @param root
     * @return
     */
    Request<?, ClientDealSize> ClientDealSize(Cid root);

    /**
     * returns the status of all ongoing transfers of data
     *
     * @return
     */
    Request<?, ClientListDataTransfers> ClientListDataTransfers();


    /**
     * attempts to restart a data transfer with the given transfer ID and other peer
     *
     * @param transferID
     * @param otherPeer
     * @param isInitiator
     * @return
     */
    Request<?, Response<Void>> ClientRestartDataTransfer(Long transferID, String otherPeer, Boolean isInitiator);

    /**
     * cancels a data transfer with the given transfer ID and other peer
     *
     * @param transferID
     * @param otherPeer
     * @param isInitiator
     * @return
     */
    Request<?, Response<Void>> ClientCancelDataTransfer(Long transferID, String otherPeer, Boolean isInitiator);

    /**
     * attempts to restart stalled retrievals on a given payment channel
     * which are stuck due to insufficient funds
     *
     * @param paymentChannel
     * @return
     */
    Request<?, Response<Void>> ClientRetrieveTryRestartInsufficientFunds(String paymentChannel);

    /**
     * cancels an ongoing retrieval deal based on DealID
     *
     * @param dealid
     * @return
     */
    Request<?, Response<Void>> ClientCancelRetrievalDeal(Long dealid);

    /**
     * lists imported files and their root CIDs
     *
     * @return
     */
    Request<?, ClientListImports> ClientListImports();

    /**
     * runs the given message and returns its result without any persisted changes.
     *
     * @param message
     * @param tipSetKey
     * @return
     */
    Request<?, StateCall> StateCall(Message message, TipSetKey tipSetKey);

    /**
     * replays a given message, assuming it was included in a block in the specified tipset.
     * <p>
     * If a tipset key is provided, and a replacing message is found on chain,
     * the method will return an error saying that the message wasn't found
     * <p>
     * If no tipset key is provided, the appropriate tipset is looked up, and if
     * the message was gas-repriced, the on-chain message will be replayed - in
     * that case the returned InvocResult.MsgCid will not match the Cid param
     * <p>
     * If the caller wants to ensure that exactly the requested message was executed,
     * they MUST check that InvocResult.MsgCid is equal to the provided Cid.
     * Without this check both the requested and original message may appear as
     * successfully executed on-chain, which may look like a double-spend.
     * <p>
     * A replacing message is a message with a different CID, any of Gas values, and
     * different signature, but with all other parameters matching (source/destination,
     * nonce, params, etc.)
     *
     * @param tipSetKey
     * @param cid
     * @return
     */
    Request<?, StateReplay> StateReplay(TipSetKey tipSetKey, Cid cid);

    /**
     * returns the indicated actor's nonce and balance.
     *
     * @param actor
     * @param tsk
     * @return
     */
    Request<?, StateGetActor> StateGetActor(String actor, TipSetKey tsk);

    /**
     * returns the indicated actor's state.
     *
     * @param actor
     * @param tsk
     * @return
     */
    Request<?, StateReadState> StateReadState(String actor, TipSetKey tsk);

    /**
     * looks back and returns all messages with a matching to or from address, stopping at the given height.
     *
     * @param match
     * @param tsk
     * @param toht
     * @return
     */
    Request<?, StateListMessages> StateListMessages(MessageMatch match, TipSetKey tsk, Long toht);

    /**
     * attempts to decode the provided params, based on the recipient actor address and method number.
     *
     * @param toAddr
     * @param method
     * @param params
     * @param tsk
     * @return
     */
    Request<?, Response<Void>> StateDecodeParams(String toAddr, Long method, String params, TipSetKey tsk);

    /**
     * returns the name of the network the node is synced to
     *
     * @return
     */
    Request<?, StateNetworkName> StateNetworkName();

    /**
     * returns info about the given miner's sectors. If the filter bitfield is nil, all sectors are included.
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerActiveSectors> StateMinerSectors(String address, TipSetKey tsk);

    /**
     * returns info about sectors that a given miner is actively proving.
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerActiveSectors> StateMinerActiveSectors(String address, TipSetKey tsk);

    /**
     * calculates the deadline at some epoch for a proving period and returns the deadline-related calculations.
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerProvingDeadline> StateMinerProvingDeadline(String address, TipSetKey tsk);

    /**
     * returns the power of the indicated miner
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerPower> StateMinerPower(String address, TipSetKey tsk);

    /**
     * returns info about the indicated miner
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerInfo> StateMinerInfo(String address, TipSetKey tsk);

    /**
     * returns all the proving deadlines for the given miner
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerDeadlines> StateMinerDeadlines(String address, TipSetKey tsk);

    /**
     * returns all partitions in the specified deadline
     *
     * @param address
     * @param dlIdx
     * @param tsk
     * @return
     */
    Request<?, StateMinerPartitions> StateMinerPartitions(String address, Long dlIdx, TipSetKey tsk);

    /**
     * returns all non-expired Faults that occur within lookback epochs of the given tipset
     *
     * @param lookback
     * @param tsk
     * @return
     */
    Request<?, StateAllMinerFaults> StateAllMinerFaults(Long lookback, TipSetKey tsk);

    /**
     * returns the precommit deposit for the specified miner's sector
     *
     * @param address
     * @param miner
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateMinerPreCommitDepositForPower(String address, SectorPreCommitInfo miner, TipSetKey tsk);

    /**
     * returns the initial pledge collateral for the specified miner's sector
     *
     * @param address
     * @param miner
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateMinerInitialPledgeCollateral(String address, SectorPreCommitInfo miner, TipSetKey tsk);

    /**
     * returns the portion of a miner's balance that can be withdrawn or spent
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateMinerAvailableBalance(String address, TipSetKey tsk);

    /**
     * checks if a sector is allocated
     *
     * @param address
     * @param sectorNumber
     * @param tsk
     * @return
     */
    Request<?, StateMinerSectorAllocated> StateMinerSectorAllocated(String address, Long sectorNumber, TipSetKey tsk);

    /**
     * returns the PreCommit info for the specified miner's sector
     *
     * @param address
     * @param sectorNumber
     * @param tsk
     * @return
     */
    Request<?, StateSectorPreCommitInfo> StateSectorPreCommitInfo(String address, Long sectorNumber, TipSetKey tsk);

    /**
     * returns the on-chain info for the specified miner's sector. Returns null in case the sector info isn't found
     * NOTE: returned info.Expiration may not be accurate in some cases, use StateSectorExpiration to get accurate
     * expiration epoch
     *
     * @param address
     * @param sectorNumber
     * @param tsk
     * @return
     */
    Request<?, StateSectorGetInfo> StateSectorGetInfo(String address, Long sectorNumber, TipSetKey tsk);

    /**
     * returns epoch at which given sector will expire
     *
     * @param address
     * @param sectorNumber
     * @param tsk
     * @return
     */
    Request<?, StateSectorExpiration> StateSectorExpiration(String address, Long sectorNumber, TipSetKey tsk);

    /**
     * finds deadline/partition with the specified sector
     *
     * @param address
     * @param sectorNumber
     * @param tsk
     * @return
     */
    Request<?, StateSectorPartition> StateSectorPartition(String address, Long sectorNumber, TipSetKey tsk);

    /**
     * searches for a message in the chain, and returns its receipt and the tipset where it was executed
     * <p>
     * NOTE: If a replacing message is found on chain, this method will return
     * a MsgLookup for the replacing message - the MsgLookup.Message will be a different
     * CID than the one provided in the 'cid' param, MsgLookup.Receipt will contain the
     * result of the execution of the replacing message.
     * <p>
     * If the caller wants to ensure that exactly the requested message was executed,
     * they MUST check that MsgLookup.Message is equal to the provided 'cid'.
     * Without this check both the requested and original message may appear as
     * successfully executed on-chain, which may look like a double-spend.
     * <p>
     * A replacing message is a message with a different CID, any of Gas values, and
     * different signature, but with all other parameters matching (source/destination,
     * nonce, params, etc.)
     *
     * @param cid
     * @return
     */
    Request<?, StateSearchMsg> StateSearchMsg(Cid cid);

    /**
     * looks back up to limit epochs in the chain for a message, and returns its receipt and the tipset where it was executed
     * <p>
     * NOTE: If a replacing message is found on chain, this method will return
     * a MsgLookup for the replacing message - the MsgLookup.Message will be a different
     * CID than the one provided in the 'cid' param, MsgLookup.Receipt will contain the
     * result of the execution of the replacing message.
     * <p>
     * If the caller wants to ensure that exactly the requested message was executed,
     * they MUST check that MsgLookup.Message is equal to the provided 'cid'.
     * Without this check both the requested and original message may appear as
     * successfully executed on-chain, which may look like a double-spend.
     * <p>
     * A replacing message is a message with a different CID, any of Gas values, and
     * different signature, but with all other parameters matching (source/destination,
     * nonce, params, etc.)
     *
     * @param msg
     * @param limit
     * @return
     */
    Request<?, StateSearchMsg> StateSearchMsgLimited(Cid msg, Long limit);

    /**
     * looks back in the chain for a message. If not found, it blocks until the
     * message arrives on chain, and gets to the indicated confidence depth.
     * <p>
     * NOTE: If a replacing message is found on chain, this method will return
     * a MsgLookup for the replacing message - the MsgLookup.Message will be a different
     * CID than the one provided in the 'cid' param, MsgLookup.Receipt will contain the
     * result of the execution of the replacing message.
     * <p>
     * If the caller wants to ensure that exactly the requested message was executed,
     * they MUST check that MsgLookup.Message is equal to the provided 'cid'.
     * Without this check both the requested and original message may appear as
     * successfully executed on-chain, which may look like a double-spend.
     * <p>
     * A replacing message is a message with a different CID, any of Gas values, and
     * different signature, but with all other parameters matching (source/destination,
     * nonce, params, etc.)
     *
     * @param cid
     * @param confidence
     * @return
     */
    Request<?, StateWaitMsg> StateWaitMsg(Cid cid, Long confidence);

    /**
     * returns the addresses of every miner that has claimed power in the Power Actor
     *
     * @param tsk
     * @return
     */
    Request<?, StateListMiners> StateListMiners(TipSetKey tsk);

    /**
     * returns the addresses of every actor in the state
     *
     * @param tsk
     * @return
     */
    Request<?, StateListActors> StateListActors(TipSetKey tsk);

    /**
     * looks up the Escrow and Locked balances of the given address in the Storage Market
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMarketBalance> StateMarketBalance(String address, TipSetKey tsk);

    /**
     * returns the Escrow and Locked balances of every participant in the Storage Market
     *
     * @param tsk
     * @return
     */
    Request<?, StateMarketParticipants> StateMarketParticipants(TipSetKey tsk);

    /**
     * returns information about every deal in the Storage Market
     *
     * @param tsk
     * @return
     */
    Request<?, StateMarketDeals> StateMarketDeals(TipSetKey tsk);

    /**
     * returns information about the indicated deal
     *
     * @param dealID
     * @param tsk
     * @return
     */
    Request<?, StateMarketStorageDeal> StateMarketStorageDeal(Long dealID, TipSetKey tsk);

    /**
     * retrieves the ID address of the given address
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateLookupID> StateLookupID(String address, TipSetKey tsk);

    /**
     * returns the public key address of the given ID address
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateAccountKey> StateAccountKey(String address, TipSetKey tsk);

    /**
     * returns the number of sectors in a miner's sector set and proving set
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, StateMinerSectorCount> StateMinerSectorCount(String address, TipSetKey tsk);

    /**
     * is a flexible command that applies the given messages on the given tipset.
     * The messages are run as though the VM were at the provided height.
     * <p>
     * When called, StateCompute will:
     * - Load the provided tipset, or use the current chain head if not provided
     * - Compute the tipset state of the provided tipset on top of the parent state
     * - (note that this step runs before vmheight is applied to the execution)
     * - Execute state upgrade if any were scheduled at the epoch, or in null
     * blocks preceding the tipset
     * - Call the cron actor on null blocks preceding the tipset
     * - For each block in the tipset
     * - Apply messages in blocks in the specified
     * - Award block reward by calling the reward actor
     * - Call the cron actor for the current epoch
     * - If the specified vmheight is higher than the current epoch, apply any
     * needed state upgrades to the state
     * - Apply the specified messages to the state
     * <p>
     * The vmheight parameter sets VM execution epoch, and can be used to simulate
     * message execution in different network versions. If the specified vmheight
     * epoch is higher than the epoch of the specified tipset, any state upgrades
     * until the vmheight will be executed on the state before applying messages
     * specified by the user.
     * <p>
     * Note that the initial tipset state computation is not affected by the
     * vmheight parameter - only the messages in the `apply` set are
     * <p>
     * If the caller wants to simply compute the state, vmheight should be set to
     * the epoch of the specified tipset.
     *
     * @param chainEpoch
     * @param msg        in the `apply` parameter must have the correct nonces, and gas
     * @param tsk        values set.
     * @return
     */
    Request<?, StateCompute> StateCompute(Long chainEpoch, List<Message> msg, TipSetKey tsk);

    /**
     * returns the data cap for the given address.
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateVerifierStatus(String address, TipSetKey tsk);

    /**
     * returns the data cap for the given address.
     *
     * @param address
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateVerifiedClientStatus(String address, TipSetKey tsk);

    /**
     * returns the address of the Verified Registry's root key
     *
     * @param tsk
     * @return
     */
    Request<?, StateVerifiedRegistryRootKey> StateVerifiedRegistryRootKey(TipSetKey tsk);

    /**
     * returns the min and max collateral a storage provider
     * can issue. It takes the deal size and verified status as parameters.
     *
     * @param paddedPieceSize
     * @param bool
     * @param tsk
     * @return
     */
    Request<?, StateDealProviderCollateralBounds> StateDealProviderCollateralBounds(Long paddedPieceSize, Boolean bool, TipSetKey tsk);

    /**
     * returns the exact circulating supply of Filecoin at the given tipset.
     * This is not used anywhere in the protocol itself, and is only for external consumption.
     *
     * @param tsk
     * @return
     */
    Request<?, BigintResp> StateCirculatingSupply(TipSetKey tsk);

    /**
     * returns an approximation of the circulating supply of Filecoin at the given tipset.
     * This is the value reported by the runtime interface to actors code.
     *
     * @param tsk
     * @return
     */
    Request<?, StateVMCirculatingSupplyInternal> StateVMCirculatingSupplyInternal(TipSetKey tsk);

    /**
     * returns the network version at the given tipset
     *
     * @param tsk
     * @return
     */
    Request<?, StateNetworkVersion> StateNetworkVersion(TipSetKey tsk);

    /**
     * returns the portion of a multisig's balance that can be withdrawn or spent
     *
     * @param multiAddress
     * @param tsk
     * @return
     */
    Request<?, BigintResp> MsigGetAvailableBalance(String multiAddress, TipSetKey tsk);

    /**
     * returns the vesting details of a given multisig.
     *
     * @param multiAddress
     * @param tsk
     * @return
     */
    Request<?, MsigGetVestingSchedule> MsigGetVestingSchedule(String multiAddress, TipSetKey tsk);

    /**
     * returns the amount of FIL that vested in a multisig in a certain period.
     *
     * @param multiAddress
     * @param start
     * @param end
     * @return
     */
    Request<?, BigintResp> MsigGetVested(String multiAddress, TipSetKey start, TipSetKey end);

    /**
     * returns pending transactions for the given multisig
     *
     * @param multiAddress
     * @param tsk
     * @return
     */
    Request<?, MsigGetPending> MsigGetPending(String multiAddress, TipSetKey tsk);

    /**
     * creates a multisig wallet
     *
     * @param required       required number of senders
     * @param signers        approving addresses
     * @param chainEpoch     unlock duration
     * @param initialBalance initial balance
     * @param sender         sender address of the create msg
     * @param gasPrice
     * @return
     */
    Request<?, MultiMessagePrototype> MsigCreate(Long required, List<String> signers, Long chainEpoch, BigInteger initialBalance, String sender, BigInteger gasPrice);

    /**
     * proposes a multisig message
     *
     * @param multiAddress multisig address
     * @param recipient    recipient address
     * @param value        value to transfer
     * @param sender       sender address of the propose msg
     * @param method       method to call in the proposed message
     * @param params       params to include in the proposed message
     * @return
     */
    Request<?, MultiMessagePrototype> MsigPropose(String multiAddress, String recipient, BigInteger value, String sender, Integer method, String params);

    /**
     * approves a previously-proposed multisig message by transaction ID
     *
     * @param multiAddress
     * @param proposedId   proposed transaction ID
     * @param signer
     * @return
     */
    Request<?, MultiMessagePrototype> MsigApprove(String multiAddress, Long proposedId, String signer);

    /**
     * @param multiAddress multisig address
     * @param proposedId   proposed message ID
     * @param proposer     proposer address
     * @param recipient    recipient address
     * @param value        value to transfer
     * @param sender       sender address of the approve msg
     * @param method       method to call in the proposed message
     * @param params       params to include in the proposed message
     * @return
     */
    Request<?, MultiMessagePrototype> MsigApproveTxnHash(String multiAddress, Long proposedId, String proposer, String recipient, BigInteger value, String sender, Integer method, String params);

    /**
     * cancels a previously-proposed multisig message
     *
     * @param multiAddress
     * @param proposedId   proposed transaction ID
     * @param recipient    recipient address
     * @param value        value to transfer
     * @param sender       sender address of the cancel msg
     * @param method       method to call in the proposed message
     * @param params       params to include in the proposed message
     * @return
     */
    Request<?, MultiMessagePrototype> MsigCancel(String multiAddress, Long proposedId, String recipient, BigInteger value, String sender, Integer method, String params);

    /**
     * proposes adding a signer in the multisig
     *
     * @param multiAddress
     * @param sender       sender address of the propose msg
     * @param newSigner    new signer
     * @param increased    whether the number of required signers should be increased
     * @return
     */
    Request<?, MultiMessagePrototype> MsigAddPropose(String multiAddress, String sender, String newSigner, Boolean increased);

    /**
     * approves a previously proposed AddSigner message
     *
     * @param multiAddress
     * @param sender       sender address of the approve msg
     * @param proposedID   proposed message ID
     * @param proposer     proposer address
     * @param newSigner    new signer
     * @param increased    whether the number of required signers should be increased
     * @return
     */
    Request<?, MultiMessagePrototype> MsigAddApprove(String multiAddress, String sender, Long proposedID, String proposer, String newSigner, Boolean increased);

    /**
     * cancels a previously proposed AddSigner message
     *
     * @param multiAddress
     * @param sender       sender address of the cancel msg
     * @param proposedID   proposed message ID
     * @param newSigner    new signer
     * @param increased    whether the number of required signers should be increased
     * @return
     */
    Request<?, MultiMessagePrototype> MsigAddCancel(String multiAddress, String sender, Long proposedID, String newSigner, Boolean increased);

    /**
     * proposes swapping 2 signers in the multisig
     *
     * @param multiAddress
     * @param sender       sender address of the propose msg
     * @param oldSigner    old signer
     * @param newSigner    new signer
     * @return
     */
    Request<?, MultiMessagePrototype> MsigSwapPropose(String multiAddress, String sender, String oldSigner, String newSigner);

    /**
     * approves a previously proposed SwapSigner
     *
     * @param multiAddress
     * @param sender       sender address of the approve msg
     * @param proposedID   proposed message ID
     * @param proposer     proposer address
     * @param oldSigner    old signer
     * @param newSigner    new signer
     * @return
     */
    Request<?, MultiMessagePrototype> MsigSwapApprove(String multiAddress, String sender, Long proposedID, String proposer, String oldSigner, String newSigner);

    /**
     * cancels a previously proposed SwapSigner message
     *
     * @param multiAddress
     * @param sender       sender address of the cancel msg
     * @param proposedID   proposed message ID
     * @param oldSigner    old signer
     * @param newSigner    new signer
     * @return
     */
    Request<?, MultiMessagePrototype> MsigSwapCancel(String multiAddress, String sender, Long proposedID, String oldSigner, String newSigner);

    /**
     * proposes the removal of a signer from the multisig
     *
     * @param multiAddress
     * @param proposer
     * @param toRemove
     * @param decrease
     * @return
     */
    Request<?, MultiMessagePrototype> MsigRemoveSigner(String multiAddress, String proposer, String toRemove, Boolean decrease);

    /**
     * gets the amount of funds that are currently reserved for the address
     *
     * @param address
     * @return
     */
    Request<?, BigintResp> MarketGetReserved(String address);

    /**
     * reserves funds for a deal
     *
     * @param wallet
     * @param address
     * @param amt
     * @return
     */
    Request<?, MarketReserveFunds> MarketReserveFunds(String wallet, String address, BigInteger amt);

    /**
     * releases funds reserved by MarketReserveFunds
     *
     * @param address
     * @param amt
     * @return
     */
    Request<?, Response<Void>> MarketReleaseFunds(String address, BigInteger amt);

    /**
     * withdraws unlocked funds from the market actor
     *
     * @param wallet
     * @param address
     * @param amt
     * @return
     */
    Request<?, MarketWithdraw> MarketWithdraw(String wallet, String address, BigInteger amt);

    Request<?, PaychGet> PaychGet(String from, String to, BigInteger amt);

    Request<?, PaychGetWaitReady> PaychGetWaitReady(Cid cid);

    Request<?, PaychAvailableFunds> PaychAvailableFunds(String address);

    Request<?, PaychAvailableFunds> PaychAvailableFundsByFromTo(String from, String to);

    Request<?, PaychList> PaychList();

    Request<?, PaychStatus> PaychStatus(String address);

    Request<?, PaychSettle> PaychSettle(String address);

    Request<?, PaychCollect> PaychCollect(String address);

    Request<?, PaychAllocateLane> PaychAllocateLane(String address);

    Request<?, PaychNewPayment> PaychNewPayment(String from, String to, List<VoucherSpec> vouchers);

    Request<?, Response<Void>> PaychVoucherCheckValid(String address, SignedVoucher paych);

    Request<?, PaychVoucherCheckSpendable> PaychVoucherCheckSpendable(String address, SignedVoucher paych, String secret, String proof);

    Request<?, PaychVoucherCreate> PaychVoucherCreate(String address, BigInteger amount, Long lane);

    Request<?, BigintResp> PaychVoucherAdd(String address, SignedVoucher paych, String params, BigInteger amt);

    Request<?, PaychVoucherList> PaychVoucherList(String address);

    Request<?, PaychVoucherSubmit> PaychVoucherSubmit(String address, SignedVoucher paych, String secret, String proof);

    Request<?, NodeStatus> NodeStatus(Boolean inclChainStatus);


    /**
     * creates node backup onder the specified file name. The
     * method requires that the lotus daemon is running with the
     * LOTUS_BACKUP_BASE_PATH environment variable set to some path, and that
     * the path specified when calling CreateBackup is within the base path
     *
     * @param fpath
     * @return
     */
    Request<?, Response<Void>> CreateBackup(String fpath);

}