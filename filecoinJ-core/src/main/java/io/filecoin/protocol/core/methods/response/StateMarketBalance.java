package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateMarketBalance extends Response<StateMarketBalance.MarketBalance> {

    @Override
    public MarketBalance getResult() {
        return super.getResult();
    }

    public static class MarketBalance implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger escrow;
        private BigInteger locked;

        public MarketBalance() {
        }

        public BigInteger getEscrow() {
            return escrow;
        }

        public void setEscrow(BigInteger escrow) {
            this.escrow = escrow;
        }

        public BigInteger getLocked() {
            return locked;
        }

        public void setLocked(BigInteger locked) {
            this.locked = locked;
        }
    }
}
