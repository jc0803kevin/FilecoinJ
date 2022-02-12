package io.filecoin.protocol.domain.paych;

import io.filecoin.crypto.types.Signature;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class SignedVoucher implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    // ChannelAddr is the address of the payment channel this signed voucher is valid for
    private String channelAddr;
    // TimeLockMin sets a min epoch before which the voucher cannot be redeemed
    private Long timeLockMin;
    // TimeLockMax sets a max epoch beyond which the voucher cannot be redeemed
    // TimeLockMax set to 0 means no timeout
    private Long timeLockMax;
    // (optional) The SecretPreImage is used by `To` to validate
    private String secretPreimage;
    // (optional) Extra can be specified by `From` to add a verification method to the voucher
    private ModVerifyParams extra;
    // Specifies which lane the Voucher merges into (will be created if does not exist)
    private Long lane;
    // Nonce is set by `From` to prevent redemption of stale vouchers on a lane
    private Long nonce;
    // Amount voucher can be redeemed for
    private String amount;
    // (optional) MinSettleHeight can extend channel MinSettleHeight if needed
    private Long MinSettleHeight;

    // (optional) Set of lanes to be merged into `Lane`
    private String merges;

    // Sender's signature over the voucher
    private Signature signature;

    public SignedVoucher() {
    }

    public String getChannelAddr() {
        return channelAddr;
    }

    public void setChannelAddr(String channelAddr) {
        this.channelAddr = channelAddr;
    }

    public Long getTimeLockMin() {
        return timeLockMin;
    }

    public void setTimeLockMin(Long timeLockMin) {
        this.timeLockMin = timeLockMin;
    }

    public Long getTimeLockMax() {
        return timeLockMax;
    }

    public void setTimeLockMax(Long timeLockMax) {
        this.timeLockMax = timeLockMax;
    }

    public String getSecretPreimage() {
        return secretPreimage;
    }

    public void setSecretPreimage(String secretPreimage) {
        this.secretPreimage = secretPreimage;
    }

    public ModVerifyParams getExtra() {
        return extra;
    }

    public void setExtra(ModVerifyParams extra) {
        this.extra = extra;
    }

    public Long getLane() {
        return lane;
    }

    public void setLane(Long lane) {
        this.lane = lane;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getMinSettleHeight() {
        return MinSettleHeight;
    }

    public void setMinSettleHeight(Long minSettleHeight) {
        MinSettleHeight = minSettleHeight;
    }

    public String getMerges() {
        return merges;
    }

    public void setMerges(String merges) {
        this.merges = merges;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }
}
