package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateVMCirculatingSupplyInternal extends Response<StateVMCirculatingSupplyInternal.CirculatingSupply> {

    @Override
    public CirculatingSupply getResult() {
        return super.getResult();
    }

    public static class CirculatingSupply implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger filVested;
        private BigInteger filMined;
        private BigInteger filBurnt;
        private BigInteger filLocked;
        private BigInteger filCirculating;
        private BigInteger filReserveDisbursed;

        public CirculatingSupply() {
        }

        public BigInteger getFilVested() {
            return filVested;
        }

        public void setFilVested(BigInteger filVested) {
            this.filVested = filVested;
        }

        public BigInteger getFilMined() {
            return filMined;
        }

        public void setFilMined(BigInteger filMined) {
            this.filMined = filMined;
        }

        public BigInteger getFilBurnt() {
            return filBurnt;
        }

        public void setFilBurnt(BigInteger filBurnt) {
            this.filBurnt = filBurnt;
        }

        public BigInteger getFilLocked() {
            return filLocked;
        }

        public void setFilLocked(BigInteger filLocked) {
            this.filLocked = filLocked;
        }

        public BigInteger getFilCirculating() {
            return filCirculating;
        }

        public void setFilCirculating(BigInteger filCirculating) {
            this.filCirculating = filCirculating;
        }

        public BigInteger getFilReserveDisbursed() {
            return filReserveDisbursed;
        }

        public void setFilReserveDisbursed(BigInteger filReserveDisbursed) {
            this.filReserveDisbursed = filReserveDisbursed;
        }
    }
}
