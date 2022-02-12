package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class StateMinerInfo extends Response<StateMinerInfo.MinerInfo> {

    @Override
    public MinerInfo getResult() {
        return super.getResult();
    }

    public static class MinerInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String owner;                       // Must be an ID-address.
        private String worker;                      // Must be an ID-address.
        private String newWorker;                   // Must be an ID-address.
        private List<String> controlAddresses;      // Must be an ID-addresses.
        private Long workerChangeEpoch;
        private String peerId;
        private List<String> multiaddrs;
        private Long windowPoStProofType;
        private Long sectorSize;
        private Long windowPoStPartitionSectors;
        private Long consensusFaultElapsed;

        public MinerInfo() {
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getWorker() {
            return worker;
        }

        public void setWorker(String worker) {
            this.worker = worker;
        }

        public String getNewWorker() {
            return newWorker;
        }

        public void setNewWorker(String newWorker) {
            this.newWorker = newWorker;
        }

        public List<String> getControlAddresses() {
            return controlAddresses;
        }

        public void setControlAddresses(List<String> controlAddresses) {
            this.controlAddresses = controlAddresses;
        }

        public Long getWorkerChangeEpoch() {
            return workerChangeEpoch;
        }

        public void setWorkerChangeEpoch(Long workerChangeEpoch) {
            this.workerChangeEpoch = workerChangeEpoch;
        }

        public String getPeerId() {
            return peerId;
        }

        public void setPeerId(String peerId) {
            this.peerId = peerId;
        }

        public List<String> getMultiaddrs() {
            return multiaddrs;
        }

        public void setMultiaddrs(List<String> multiaddrs) {
            this.multiaddrs = multiaddrs;
        }

        public Long getWindowPoStProofType() {
            return windowPoStProofType;
        }

        public void setWindowPoStProofType(Long windowPoStProofType) {
            this.windowPoStProofType = windowPoStProofType;
        }

        public Long getSectorSize() {
            return sectorSize;
        }

        public void setSectorSize(Long sectorSize) {
            this.sectorSize = sectorSize;
        }

        public Long getWindowPoStPartitionSectors() {
            return windowPoStPartitionSectors;
        }

        public void setWindowPoStPartitionSectors(Long windowPoStPartitionSectors) {
            this.windowPoStPartitionSectors = windowPoStPartitionSectors;
        }

        public Long getConsensusFaultElapsed() {
            return consensusFaultElapsed;
        }

        public void setConsensusFaultElapsed(Long consensusFaultElapsed) {
            this.consensusFaultElapsed = consensusFaultElapsed;
        }
    }
}
