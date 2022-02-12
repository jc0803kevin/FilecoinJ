package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class PaychAvailableFunds extends Response<PaychAvailableFunds.ChannelAvailableFunds> {

    @Override
    public ChannelAvailableFunds getResult() {
        return super.getResult();
    }

    public static class ChannelAvailableFunds implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        // Channel is the address of the channel
        private String channel;
        // From is the from address of the channel (channel creator)
        private String from;
        // To is the to address of the channel
        private String to;
        // ConfirmedAmt is the amount of funds that have been confirmed on-chain
        // for the channel
        private BigInteger confirmedAmt;
        // PendingAmt is the amount of funds that are pending confirmation on-chain
        private BigInteger pendingAmt;
        // PendingWaitSentinel can be used with PaychGetWaitReady to wait for
        // confirmation of pending funds
        private Cid pendingWaitSentinel;
        // QueuedAmt is the amount that is queued up behind a pending request
        private BigInteger queuedAmt;
        // VoucherRedeemedAmt is the amount that is redeemed by vouchers on-chain
        // and in the local datastore
        private BigInteger voucherReedeemedAmt;

        public ChannelAvailableFunds() {
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigInteger getConfirmedAmt() {
            return confirmedAmt;
        }

        public void setConfirmedAmt(BigInteger confirmedAmt) {
            this.confirmedAmt = confirmedAmt;
        }

        public BigInteger getPendingAmt() {
            return pendingAmt;
        }

        public void setPendingAmt(BigInteger pendingAmt) {
            this.pendingAmt = pendingAmt;
        }

        public Cid getPendingWaitSentinel() {
            return pendingWaitSentinel;
        }

        public void setPendingWaitSentinel(Cid pendingWaitSentinel) {
            this.pendingWaitSentinel = pendingWaitSentinel;
        }

        public BigInteger getQueuedAmt() {
            return queuedAmt;
        }

        public void setQueuedAmt(BigInteger queuedAmt) {
            this.queuedAmt = queuedAmt;
        }

        public BigInteger getVoucherReedeemedAmt() {
            return voucherReedeemedAmt;
        }

        public void setVoucherReedeemedAmt(BigInteger voucherReedeemedAmt) {
            this.voucherReedeemedAmt = voucherReedeemedAmt;
        }
    }
}
