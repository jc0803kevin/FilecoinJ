package io.filecoin.protocol.core.methods.request;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.paych.ModVerifyParams;

import java.io.Serializable;

public class VoucherSpec implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String amount;
    private Long timeLockMin;
    private Long timeLockMax;
    private Long minSettle;
    private ModVerifyParams extra;

    public VoucherSpec() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public Long getMinSettle() {
        return minSettle;
    }

    public void setMinSettle(Long minSettle) {
        this.minSettle = minSettle;
    }

    public ModVerifyParams getExtra() {
        return extra;
    }

    public void setExtra(ModVerifyParams extra) {
        this.extra = extra;
    }
}
