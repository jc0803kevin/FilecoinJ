package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.builtin.miner.SectorPreCommitInfo;

import java.io.Serializable;
import java.math.BigInteger;

public class StateSectorPreCommitInfo extends Response<StateSectorPreCommitInfo.SectorPreCommitOnChainInfo> {

    @Override
    public SectorPreCommitOnChainInfo getResult() {
        return super.getResult();
    }

    public static class SectorPreCommitOnChainInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private SectorPreCommitInfo info;
        private BigInteger preCommitDeposit;
        private Long preCommitEpoch;
        private BigInteger dealWeight;
        private BigInteger verifiedDealWeight;


        public SectorPreCommitOnChainInfo() {
        }

        public SectorPreCommitInfo getInfo() {
            return info;
        }

        public void setInfo(SectorPreCommitInfo info) {
            this.info = info;
        }

        public BigInteger getPreCommitDeposit() {
            return preCommitDeposit;
        }

        public void setPreCommitDeposit(BigInteger preCommitDeposit) {
            this.preCommitDeposit = preCommitDeposit;
        }

        public Long getPreCommitEpoch() {
            return preCommitEpoch;
        }

        public void setPreCommitEpoch(Long preCommitEpoch) {
            this.preCommitEpoch = preCommitEpoch;
        }

        public BigInteger getDealWeight() {
            return dealWeight;
        }

        public void setDealWeight(BigInteger dealWeight) {
            this.dealWeight = dealWeight;
        }

        public BigInteger getVerifiedDealWeight() {
            return verifiedDealWeight;
        }

        public void setVerifiedDealWeight(BigInteger verifiedDealWeight) {
            this.verifiedDealWeight = verifiedDealWeight;
        }
    }

}
