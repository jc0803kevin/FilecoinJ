package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class MpoolConfig extends Response<MpoolConfig.Config> {

    @Override
    public Config getResult() {
        return super.getResult();
    }

    public static class Config implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private List<String> priorityAddrs;

        private Integer sizeLimitHigh;

        private Integer sizeLimitLow;

        private Double replaceByFeeRatio;

        /**
         * 单位：ns
         */
        private Long pruneCooldown;

        private Double gasLimitOverestimation;


        public Config() {
        }

        public List<String> getPriorityAddrs() {
            return priorityAddrs;
        }

        public void setPriorityAddrs(List<String> priorityAddrs) {
            this.priorityAddrs = priorityAddrs;
        }

        public Integer getSizeLimitHigh() {
            return sizeLimitHigh;
        }

        public void setSizeLimitHigh(Integer sizeLimitHigh) {
            this.sizeLimitHigh = sizeLimitHigh;
        }

        public Integer getSizeLimitLow() {
            return sizeLimitLow;
        }

        public void setSizeLimitLow(Integer sizeLimitLow) {
            this.sizeLimitLow = sizeLimitLow;
        }

        public Double getReplaceByFeeRatio() {
            return replaceByFeeRatio;
        }

        public void setReplaceByFeeRatio(Double replaceByFeeRatio) {
            this.replaceByFeeRatio = replaceByFeeRatio;
        }

        public Long getPruneCooldown() {
            return pruneCooldown;
        }

        public void setPruneCooldown(Long pruneCooldown) {
            this.pruneCooldown = pruneCooldown;
        }

        public Double getGasLimitOverestimation() {
            return gasLimitOverestimation;
        }

        public void setGasLimitOverestimation(Double gasLimitOverestimation) {
            this.gasLimitOverestimation = gasLimitOverestimation;
        }
    }
}
