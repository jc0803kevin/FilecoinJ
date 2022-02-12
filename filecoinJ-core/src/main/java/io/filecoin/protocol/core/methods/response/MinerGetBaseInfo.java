package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.abi.RegisteredSealProof;
import io.filecoin.protocol.domain.types.BeaconEntry;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class MinerGetBaseInfo extends Response<MinerGetBaseInfo.BaseInfo> {

    @Override
    public BaseInfo getResult() {
        return super.getResult();
    }

    public static class BaseInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger minerPower;

        private BigInteger networkPower;

        private List<SectorInfo> sectors;

        private String workerKey;

        private Long sectorSize;

        private BeaconEntry prevBeaconEntry;

        private List<BeaconEntry> beaconEntries;

        private Boolean eligibleForMining;

        public BaseInfo() {
        }

        public BigInteger getMinerPower() {
            return minerPower;
        }

        public void setMinerPower(BigInteger minerPower) {
            this.minerPower = minerPower;
        }

        public BigInteger getNetworkPower() {
            return networkPower;
        }

        public void setNetworkPower(BigInteger networkPower) {
            this.networkPower = networkPower;
        }

        public List<SectorInfo> getSectors() {
            return sectors;
        }

        public void setSectors(List<SectorInfo> sectors) {
            this.sectors = sectors;
        }

        public String getWorkerKey() {
            return workerKey;
        }

        public void setWorkerKey(String workerKey) {
            this.workerKey = workerKey;
        }

        public Long getSectorSize() {
            return sectorSize;
        }

        public void setSectorSize(Long sectorSize) {
            this.sectorSize = sectorSize;
        }

        public BeaconEntry getPrevBeaconEntry() {
            return prevBeaconEntry;
        }

        public void setPrevBeaconEntry(BeaconEntry prevBeaconEntry) {
            this.prevBeaconEntry = prevBeaconEntry;
        }

        public List<BeaconEntry> getBeaconEntries() {
            return beaconEntries;
        }

        public void setBeaconEntries(List<BeaconEntry> beaconEntries) {
            this.beaconEntries = beaconEntries;
        }

        public Boolean getEligibleForMining() {
            return eligibleForMining;
        }

        public void setEligibleForMining(Boolean eligibleForMining) {
            this.eligibleForMining = eligibleForMining;
        }
    }

    public static class SectorInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private RegisteredSealProof sealProof;

        private Long sectorNumber;

        private Cid sealedCID;

        public SectorInfo() {
        }

        public RegisteredSealProof getSealProof() {
            return sealProof;
        }

        public void setSealProof(RegisteredSealProof sealProof) {
            this.sealProof = sealProof;
        }

        public Long getSectorNumber() {
            return sectorNumber;
        }

        public void setSectorNumber(Long sectorNumber) {
            this.sectorNumber = sectorNumber;
        }

        public Cid getSealedCID() {
            return sealedCID;
        }

        public void setSealedCID(Cid sealedCID) {
            this.sealedCID = sealedCID;
        }
    }

}
