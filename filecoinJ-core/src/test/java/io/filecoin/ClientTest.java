package io.filecoin;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.core.methods.request.FileRef;
import io.filecoin.protocol.core.methods.request.RetrievalOrder;
import io.filecoin.protocol.core.methods.request.StartDealParams;
import io.filecoin.protocol.core.methods.response.*;
import io.filecoin.protocol.domain.storagemarket.DataRef;
import io.filecoin.protocol.domain.types.RetrievalPeer;
import io.filecoin.protocol.utils.JsonUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class ClientTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(FileCoinJTest.class);

    @Test
    public void ClientImport() throws Exception {
        FileRef fileRef = new FileRef();
        fileRef.setPath("/home/ubuntu/filecoin/importdata/kevin.txt");
        fileRef.setCAR(false);

        ClientImport result = filecoinJ.ClientImport(fileRef).send();

        //{"importID":1636621356671294550,"root":{"/":"bafkqadbkfivcukrkfivcukrkbi"}}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientImport result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientListImports() throws Exception {
        ClientListImports result = filecoinJ.ClientListImports().send();

        //[{"carPath":"/home/ubuntu/.lotus/imports/1636621356671294549.car","err":"","filePath":"/home/ubuntu/filecoin/importdata/kevin.txt","key":1636621356671294549,"root":{"/":"bafkqadbkfivcukrkfivcukrkbi"},"source":"import"},{"carPath":"/home/ubuntu/.lotus/imports/1636621356671294550.car","err":"","filePath":"/home/ubuntu/filecoin/importdata/kevin.txt","key":1636621356671294550,"root":{"/":"bafkqadbkfivcukrkfivcukrkbi"},"source":"import"}]
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientImport result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientRemoveImport() throws Exception {
        Response result = filecoinJ.ClientRemoveImport(1636621356671294549L).send();
        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ClientStartDeal() throws Exception {

        DataRef data = new DataRef();
        data.setTransferType("graphsync");
        data.setRoot(Cid.of("bafkqadbkfivcukrkfivcukrkbi"));

        StartDealParams params = new StartDealParams();
        params.setData(data);
        params.setWallet("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        params.setMiner("t08334");
        params.setEpochPrice("0");
        params.setMinBlocksDuration(42L);
        params.setProviderCollateral("0");
        params.setDealStartEpoch(0L);
        params.setFastRetrieval(false);
        params.setVerifiedDeal(false);


        ClientStartDeal result = filecoinJ.ClientStartDeal(params).send();
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientStartDeal result-->" + JsonUtils.toJsonString(result.getResult()));

        //{"jsonrpc":"2.0","result":{"/":"bafyreic466esr2kdjt62hmgqecczlbe3tezvjmu7ufqiwjjvicp5jn3mzu"},"id":0}
        //{"jsonrpc":"2.0","result":{"/":"bafyreihakeirgmqd4kyjpytevx2inde4wvrn3srvpr46j4klerth5dicfu"},"id":0}
    }

    @Test
    public void ClientStatelessDeal() throws Exception {

        DataRef data = new DataRef();
        data.setTransferType("manual");//filecoin-project\go-fil-markets@v1.5.0\storagemarket\types.go
        data.setRoot(Cid.of("bafkqadbkfivcukrkfivcukrkbi"));

        StartDealParams params = new StartDealParams();
        params.setData(data);
        params.setWallet("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        params.setMiner("t08334");
        params.setEpochPrice("0");
        params.setMinBlocksDuration(42L);
        params.setProviderCollateral("0");
        params.setDealStartEpoch(0L);
        params.setFastRetrieval(false);
        params.setVerifiedDeal(false);


        ClientStatelessDeal result = filecoinJ.ClientStatelessDeal(params).send();
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientStatelessDeal result-->" + JsonUtils.toJsonString(result.getResult()));
    }


    @Test
    public void ClientGetDealInfo() throws Exception {
        ClientGetDealInfo result = filecoinJ.ClientGetDealInfo(Cid.of("bafyreihakeirgmqd4kyjpytevx2inde4wvrn3srvpr46j4klerth5dicfu")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientGetDealInfo result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientListDeals() throws Exception {
        ClientListDeals result = filecoinJ.ClientListDeals().send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientGetDealInfo result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientGetDealStatus() throws Exception {
        ClientGetDealStatus result = filecoinJ.ClientGetDealStatus(9L).send();

        // 0 StorageDealUnknown
        // 1 StorageDealProposalNotFound
        // 2 StorageDealProposalRejected
        // 3 StorageDealProposalAccepted
        // 4 StorageDealStaged
        // 5 StorageDealSealing
        // 6 StorageDealFinalizing
        // 7 StorageDealActive
        // 8 StorageDealExpired
        // 9 StorageDealSlashed
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientGetDealUpdates result-->" + JsonUtils.toJsonString(result.getResult()));
    }


    @Test
    public void ClientHasLocal() throws Exception {
        ClientHasLocal result = filecoinJ.ClientHasLocal(Cid.of("bafkqadbkfivcukrkfivcukrkbi")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientHasLocal result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientFindData() throws Exception {
        ClientFindData result = filecoinJ.ClientFindData(Cid.of("bafkqadbkfivcukrkfivcukrkbi"), Cid.of("baga6ea4seaqkvl4jxnii5s6wtmkgsiyoaq36nx6f4ef6u6fmmqmjnrpqj2toaaq")).send();
//        ClientFindData result = filecoinJ.ClientFindData(Cid.of("bafkqadbkfivcukrkfivcukrkbi"), null).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientFindData result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientMinerQueryOffer() throws Exception {
        ClientMinerQueryOffer result = filecoinJ
                .ClientMinerQueryOffer("t04554", Cid.of("bafkqadbkfivcukrkfivcukrkbi"),
                        Cid.of("baga6ea4seaqkvl4jxnii5s6wtmkgsiyoaq36nx6f4ef6u6fmmqmjnrpqj2toaaq")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientMinerQueryOffer result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientDealPieceCID() throws Exception {
        ClientDealPieceCID result = filecoinJ.ClientDealPieceCID(Cid.of("bafkqadbkfivcukrkfivcukrkbi")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientDealPieceCID result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientRetrieve() throws Exception {
        RetrievalOrder order = new RetrievalOrder();
        order.setRoot(Cid.of("bafkqadbkfivcukrkfivcukrkbi"));
        order.setPiece(Cid.of("baga6ea4seaqkvl4jxnii5s6wtmkgsiyoaq36nx6f4ef6u6fmmqmjnrpqj2toaaq"));
        order.setSize(128L);
        order.setTotal("0");
        order.setUnsealPrice("0");
        order.setPaymentInterval(42L);
        order.setPaymentIntervalIncrease(42L);
        order.setClient("t08334");
        order.setMiner("t08334");

        RetrievalPeer retrievalPeer = new RetrievalPeer();
        retrievalPeer.setAddress("t08334");
        retrievalPeer.setiD("12D3KooWLs9ykn4qeFGXGUmo9j7JEP5u3u7vbk7Kwf8c9bhYUDeD");
        retrievalPeer.setPieceCID(Cid.of("baga6ea4seaqkvl4jxnii5s6wtmkgsiyoaq36nx6f4ef6u6fmmqmjnrpqj2toaaq"));

        order.setMinerPeer(retrievalPeer);

        FileRef ref = new FileRef();
        ref.setCAR(false);
        ref.setPath("/home/ubuntu/filecoin/importdata/kevin.txt");
        Response result = filecoinJ.ClientRetrieve(order, ref).send();

        log.info("RawResponse-->" + result.getRawResponse());
    }


    @Test
    public void ClientListRetrievals() throws Exception {
        ClientRetrievalInfo result = filecoinJ.ClientListRetrievals().send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientListRetrievals result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientQueryAsk() throws Exception {
        ClientQueryAsk result = filecoinJ.ClientQueryAsk("12D3KooWRUBdWhUC7oHY5bEX7sic3FNV6XavjQzTGyJNaBLe9cT6", "t08334").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientQueryAsk result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientCalcCommP() throws Exception {
        ClientCalcCommP result = filecoinJ.ClientCalcCommP("/home/ubuntu/.lotus/imports/1636621356671294550.car").send();

        //{"root":{"/":"baga6ea4seaqnchegifmjfujjmqdbb6uzdaquuxak5ao6xdzg6nt4izntmwrkafa"},"size":127}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientCalcCommP result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientGenCar() throws Exception {
        FileRef ref = new FileRef();
        ref.setCAR(false);
        ref.setPath("/home/ubuntu/filecoin/importdata/kevin.txt");

        Response result = filecoinJ.ClientGenCar(ref, "/home/ubuntu/.lotus/imports/out.txt").send();

        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ClientDealSize() throws Exception {

        ClientDealSize result = filecoinJ.ClientDealSize(Cid.of("bafkqadbkfivcukrkfivcukrkbi")).send();
//        {"jsonrpc":"2.0","result":{"PayloadSize":67,"PieceSize":128},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientDealSize result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientListDataTransfers() throws Exception {

        ClientListDataTransfers result = filecoinJ.ClientListDataTransfers().send();
//        {"jsonrpc":"2.0","result":[{"TransferID":1636621356671034024,"Status":0,"BaseCID":{"/":"bafkqadbkfivcukrkfivcukrkbi"},"IsInitiator":true,"IsSender":false,"Voucher":"{\"PayloadCID\":{\"/\":\"bafkqadbkfivcukrkfivcukrkbi\"},\"ID\":1636621356671602723,\"Selector\":{\"Raw\":\"oWFSomFsoWRub25loGI6PqFhYaFhPqFhQKA=\"},\"PieceCID\":{\"/\":\"baga6ea4seaqkvl4jxnii5s6wtmkgsiyoaq36nx6f4ef6u6fmmqmjnrpqj2toaaq\"},\"PricePerByte\":\"0\",\"PaymentInterval\":42,\"PaymentIntervalIncrease\":42,\"UnsealPrice\":\"0\"}","Message":"cant open message sender to peer 12D3KooWLs9ykn4qeFGXGUmo9j7JEP5u3u7vbk7Kwf8c9bhYUDeD: routing: not found","OtherPeer":"12D3KooWLs9ykn4qeFGXGUmo9j7JEP5u3u7vbk7Kwf8c9bhYUDeD","Transferred":0,"Stages":null}],"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("ClientDealSize result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void ClientRestartDataTransfer() throws Exception {

        Response result = filecoinJ.ClientRestartDataTransfer(1636621356671034024L, "12D3KooWLs9ykn4qeFGXGUmo9j7JEP5u3u7vbk7Kwf8c9bhYUDeD", true).send();
        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ClientCancelDataTransfer() throws Exception {

        Response result = filecoinJ.ClientCancelDataTransfer(1636621356671034024L, "12D3KooWLs9ykn4qeFGXGUmo9j7JEP5u3u7vbk7Kwf8c9bhYUDeD", true).send();
        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ClientRetrieveTryRestartInsufficientFunds() throws Exception {

        Response result = filecoinJ.ClientRetrieveTryRestartInsufficientFunds("1636621356671034024").send();
        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void ClientCancelRetrievalDeal() throws Exception {

        Response result = filecoinJ.ClientCancelRetrievalDeal(1636621356671602723L).send();
        log.info("RawResponse-->" + result.getRawResponse());
    }

    @Test
    public void CreateBackup() throws Exception {

        Response result = filecoinJ.CreateBackup("").send();
        log.info("RawResponse-->" + result.getRawResponse());
    }
}
