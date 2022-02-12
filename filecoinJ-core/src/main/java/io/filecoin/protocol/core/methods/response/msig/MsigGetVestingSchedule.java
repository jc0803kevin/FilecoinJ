package io.filecoin.protocol.core.methods.response.msig;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class MsigGetVestingSchedule extends Response<MsigGetVestingSchedule.MsigVesting> {

    @Override
    public MsigVesting getResult() {
        return super.getResult();
    }

    public static class MsigVesting implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger initialBalance;
        private Long startEpoch;
        private Long unlockDuration;

        public MsigVesting() {
        }

        public BigInteger getInitialBalance() {
            return initialBalance;
        }

        public void setInitialBalance(BigInteger initialBalance) {
            this.initialBalance = initialBalance;
        }

        public Long getStartEpoch() {
            return startEpoch;
        }

        public void setStartEpoch(Long startEpoch) {
            this.startEpoch = startEpoch;
        }

        public Long getUnlockDuration() {
            return unlockDuration;
        }

        public void setUnlockDuration(Long unlockDuration) {
            this.unlockDuration = unlockDuration;
        }
    }
}
