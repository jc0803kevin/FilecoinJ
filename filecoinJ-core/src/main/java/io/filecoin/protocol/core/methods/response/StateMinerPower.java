package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateMinerPower extends Response<StateMinerPower.MinerPower> {

    @Override
    public MinerPower getResult() {
        return super.getResult();
    }

    public static class MinerPower implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private StoragePower minerPower;
        private StoragePower totalPower;
        private Boolean hasMinPower;

        public MinerPower() {
        }

        public StoragePower getMinerPower() {
            return minerPower;
        }

        public void setMinerPower(StoragePower minerPower) {
            this.minerPower = minerPower;
        }

        public StoragePower getTotalPower() {
            return totalPower;
        }

        public void setTotalPower(StoragePower totalPower) {
            this.totalPower = totalPower;
        }

        public Boolean getHasMinPower() {
            return hasMinPower;
        }

        public void setHasMinPower(Boolean hasMinPower) {
            this.hasMinPower = hasMinPower;
        }
    }

    public static class StoragePower implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;
        private BigInteger rawBytePower;
        private BigInteger qualityAdjPower;

        public StoragePower() {
        }

        public BigInteger getRawBytePower() {
            return rawBytePower;
        }

        public void setRawBytePower(BigInteger rawBytePower) {
            this.rawBytePower = rawBytePower;
        }

        public BigInteger getQualityAdjPower() {
            return qualityAdjPower;
        }

        public void setQualityAdjPower(BigInteger qualityAdjPower) {
            this.qualityAdjPower = qualityAdjPower;
        }
    }
}
