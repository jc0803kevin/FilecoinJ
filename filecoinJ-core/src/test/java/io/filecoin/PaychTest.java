package io.filecoin;

import com.google.common.collect.Lists;
import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Signature;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.core.methods.request.VoucherSpec;
import io.filecoin.protocol.core.methods.response.*;
import io.filecoin.protocol.domain.paych.SignedVoucher;
import io.filecoin.protocol.utils.JsonUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

@Ignore
public class PaychTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(PaychTest.class);

    @Test
    public void MarketGetReserved() throws Exception {
        BigintResp result = filecoinJ.MarketGetReserved("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MarketReserveFunds() throws Exception {
        MarketReserveFunds result = filecoinJ.MarketReserveFunds("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", "t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi", new BigInteger("0")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MarketReleaseFunds() throws Exception {
        Response result = filecoinJ.MarketReleaseFunds("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", new BigInteger("0")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void MarketWithdraw() throws Exception {

        //"PrivateKey": "83YjFDNRREyp6K4+TNVLuue7csVNzgjatJWEXdnFrWU="
        MarketWithdraw result = filecoinJ.MarketWithdraw(
                "t3wp5dy4laxr3fpi2qzeo76wl5uclek7bgjafodyxfv5xe2z3etbniagqr3nt3pufiao4ui6m6n3vulfejunka",
                "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri", new BigInteger("1")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychList() throws Exception {
        PaychList result = filecoinJ.PaychList().send();

        //{"jsonrpc":"2.0","result":["t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi"],"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychStatus() throws Exception {
        PaychStatus result = filecoinJ.PaychStatus("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

        //{"jsonrpc":"2.0","result":{"ControlAddr":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","Direction":2},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychGet() throws Exception {
        PaychGet result = filecoinJ.PaychGet("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri", new BigInteger("4")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychGetWaitReady() throws Exception {
        PaychGetWaitReady result = filecoinJ.PaychGetWaitReady(Cid.of("bafy2bzacecdxxjxjllnd7swxmq2onzmbkh5vdxfsv3pa4hpi6z4aecxn7s67q")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychAvailableFunds() throws Exception {
        PaychAvailableFunds result = filecoinJ.PaychAvailableFunds("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

//        {"jsonrpc":"2.0","result":{"Channel":"t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi","From":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","To":"t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri","ConfirmedAmt":"1004","PendingAmt":"0","PendingWaitSentinel":null,"QueuedAmt":"0","VoucherReedeemedAmt":"0"},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychAvailableFundsByFromTo() throws Exception {
        PaychAvailableFunds result = filecoinJ.PaychAvailableFundsByFromTo("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri").send();

//        {"jsonrpc":"2.0","result":{"Channel":null,"From":"t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy","To":"t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri","ConfirmedAmt":"0","PendingAmt":"0","PendingWaitSentinel":null,"QueuedAmt":"0","VoucherReedeemedAmt":"0"},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychSettle() throws Exception {
        PaychSettle result = filecoinJ.PaychSettle("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

        //{"jsonrpc":"2.0","result":{"/":"bafy2bzacedchnh26drdpppidthhcpq3h5c3z6me5pjlsdxcilum3hggq4s4z2"},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychCollect() throws Exception {
        PaychCollect result = filecoinJ.PaychCollect("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychAllocateLane() throws Exception {
        PaychAllocateLane result = filecoinJ.PaychAllocateLane("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();
        //{"jsonrpc":"2.0","result":1,"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychNewPayment() throws Exception {

        VoucherSpec voucherSpec = new VoucherSpec();
        voucherSpec.setAmount("1");
        voucherSpec.setMinSettle(0L);
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setTimeLockMin(0L);

        PaychNewPayment result = filecoinJ.PaychNewPayment("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy",
                "t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri", Lists.newArrayList(voucherSpec)).send();

        //{"jsonrpc":"2.0","result":{"Channel":"t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a","WaitSentinel":{"/":"bafy2bzaceafyenvpzfvajxoidmhkn3ch5vvp4ibwekdlcfuf6cl2lh6ygtkuo"},"Vouchers":[{"ChannelAddr":"t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a","TimeLockMin":0,"TimeLockMax":0,"SecretPreimage":null,"Extra":null,"Lane":4,"Nonce":1,"Amount":"1","MinSettleHeight":0,"Merges":null,"Signature":{"Type":1,"Data":"9Jwqi5YuW14T/QEse2heRYVkBqM4gdPhMBJC8C0f8CcoPu3rUwN/6RMN+m86HfmS+LWwG00287StSjsPTWgbVgA="}}]},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherCheckValid() throws Exception {

        SignedVoucher voucherSpec = new SignedVoucher();
        voucherSpec.setChannelAddr("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a");
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setLane(3L);
        voucherSpec.setAmount("1");
        voucherSpec.setNonce(1L);

        Signature signature = new Signature();
        signature.setType(1);
        signature.setData("c/9AYA9Q12kGnPP22XCAqrAzl81/0WAnuMCVPnRiQkN6CskBit6QwuPf61erPWUFvizqxKWEI02MJgwYQxQnOwE=");

        voucherSpec.setSignature(signature);

        Response result = filecoinJ.PaychVoucherCheckValid("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a",
                voucherSpec).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherCheckSpendable() throws Exception {

        SignedVoucher voucherSpec = new SignedVoucher();
        voucherSpec.setChannelAddr("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a");
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setLane(3L);
        voucherSpec.setAmount("1");
        voucherSpec.setNonce(1L);

        Signature signature = new Signature();
        signature.setType(1);
        signature.setData("c/9AYA9Q12kGnPP22XCAqrAzl81/0WAnuMCVPnRiQkN6CskBit6QwuPf61erPWUFvizqxKWEI02MJgwYQxQnOwE=");

        voucherSpec.setSignature(signature);

        //{"jsonrpc":"2.0","result":true,"id":0}
        PaychVoucherCheckSpendable result = filecoinJ.PaychVoucherCheckSpendable("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a", voucherSpec, "", "").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherSubmit() throws Exception {

        SignedVoucher voucherSpec = new SignedVoucher();
        voucherSpec.setChannelAddr("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a");
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setLane(3L);
        voucherSpec.setAmount("1");
        voucherSpec.setNonce(1L);

        Signature signature = new Signature();
        signature.setType(1);
        signature.setData("c/9AYA9Q12kGnPP22XCAqrAzl81/0WAnuMCVPnRiQkN6CskBit6QwuPf61erPWUFvizqxKWEI02MJgwYQxQnOwE=");

        voucherSpec.setSignature(signature);

        PaychVoucherSubmit result = filecoinJ.PaychVoucherSubmit("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a", voucherSpec, "", "").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherAdd() throws Exception {

        SignedVoucher voucherSpec = new SignedVoucher();
        voucherSpec.setChannelAddr("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a");
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setTimeLockMax(0L);
        voucherSpec.setLane(3L);
        voucherSpec.setAmount("1");
        voucherSpec.setNonce(1L);

        Signature signature = new Signature();
        signature.setType(1);
        signature.setData("c/9AYA9Q12kGnPP22XCAqrAzl81/0WAnuMCVPnRiQkN6CskBit6QwuPf61erPWUFvizqxKWEI02MJgwYQxQnOwE=");

        voucherSpec.setSignature(signature);

        //{"jsonrpc":"2.0","result":"0","id":0}
        BigintResp result = filecoinJ.PaychVoucherAdd("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a", voucherSpec, "", new BigInteger("1")).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherCreate() throws Exception {

        //{"jsonrpc":"2.0","result":{"Voucher":{"ChannelAddr":"t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a","TimeLockMin":0,"TimeLockMax":0,"SecretPreimage":null,"Extra":null,"Lane":3,"Nonce":1,"Amount":"1","MinSettleHeight":0,"Merges":null,"Signature":{"Type":1,"Data":"c/9AYA9Q12kGnPP22XCAqrAzl81/0WAnuMCVPnRiQkN6CskBit6QwuPf61erPWUFvizqxKWEI02MJgwYQxQnOwE="}},"Shortfall":"0"},"id":0}
        PaychVoucherCreate result = filecoinJ.PaychVoucherCreate("t2lfwuhzfdsjvytzidfftlvgvirpqubohjymctf2a", new BigInteger("1"), 3L).send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void PaychVoucherList() throws Exception {

        SignedVoucher voucherSpec = new SignedVoucher();

        PaychVoucherList result = filecoinJ.PaychVoucherList("t2t6m2yxkfgnatioljndwkdh5n5nvho7kongxpgpi").send();

        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }

    @Test
    public void NodeStatus() throws Exception {

        NodeStatus result = filecoinJ.NodeStatus(true).send();
//        {"jsonrpc":"2.0","result":{"SyncStatus":{"Epoch":445053,"Behind":0},"PeerStatus":{"PeersToPublishMsgs":49,"PeersToPublishBlocks":49},"ChainStatus":{"BlocksPerTipsetLast100":0,"BlocksPerTipsetLastFinality":0}},"id":0}
//        {"jsonrpc":"2.0","result":{"SyncStatus":{"Epoch":445053,"Behind":0},"PeerStatus":{"PeersToPublishMsgs":49,"PeersToPublishBlocks":49},"ChainStatus":{"BlocksPerTipsetLast100":3.57,"BlocksPerTipsetLastFinality":3.7533333333333334}},"id":0}
        log.info("RawResponse-->" + result.getRawResponse());
        log.info("result-->" + JsonUtils.toJsonString(result.getResult()));
    }
}
