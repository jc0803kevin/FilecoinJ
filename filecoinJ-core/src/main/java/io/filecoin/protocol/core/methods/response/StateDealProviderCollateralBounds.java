package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateDealProviderCollateralBounds extends Response<StateDealProviderCollateralBounds.DealCollateralBounds> {

    @Override
    public DealCollateralBounds getResult() {
        return super.getResult();
    }

    public static class DealCollateralBounds implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger min;
        private BigInteger max;

        public DealCollateralBounds() {
        }

        public BigInteger getMin() {
            return min;
        }

        public void setMin(BigInteger min) {
            this.min = min;
        }

        public BigInteger getMax() {
            return max;
        }

        public void setMax(BigInteger max) {
            this.max = max;
        }
    }
}
