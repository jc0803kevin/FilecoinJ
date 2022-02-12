package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigDecimal;

public class NodeStatus extends Response<NodeStatus.Status> {

    @Override
    public Status getResult() {
        return super.getResult();
    }

    public static class Status implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private NodeSyncStatus syncStatus;
        private NodePeerStatus peerStatus;
        private NodeChainStatus chainStatus;

        public Status() {
        }

        public NodeSyncStatus getSyncStatus() {
            return syncStatus;
        }

        public void setSyncStatus(NodeSyncStatus syncStatus) {
            this.syncStatus = syncStatus;
        }

        public NodePeerStatus getPeerStatus() {
            return peerStatus;
        }

        public void setPeerStatus(NodePeerStatus peerStatus) {
            this.peerStatus = peerStatus;
        }

        public NodeChainStatus getChainStatus() {
            return chainStatus;
        }

        public void setChainStatus(NodeChainStatus chainStatus) {
            this.chainStatus = chainStatus;
        }
    }

    public static class NodeSyncStatus implements Serializable{
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long epoch;
        private Long behind;

        public NodeSyncStatus() {
        }

        public Long getEpoch() {
            return epoch;
        }

        public void setEpoch(Long epoch) {
            this.epoch = epoch;
        }

        public Long getBehind() {
            return behind;
        }

        public void setBehind(Long behind) {
            this.behind = behind;
        }
    }

    public static class NodePeerStatus implements Serializable{
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Integer peersToPublishMsgs;
        private Integer peersToPublishBlocks;

        public NodePeerStatus() {
        }

        public Integer getPeersToPublishMsgs() {
            return peersToPublishMsgs;
        }

        public void setPeersToPublishMsgs(Integer peersToPublishMsgs) {
            this.peersToPublishMsgs = peersToPublishMsgs;
        }

        public Integer getPeersToPublishBlocks() {
            return peersToPublishBlocks;
        }

        public void setPeersToPublishBlocks(Integer peersToPublishBlocks) {
            this.peersToPublishBlocks = peersToPublishBlocks;
        }
    }

    public static class NodeChainStatus implements Serializable{
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigDecimal blocksPerTipsetLast100;
        private BigDecimal blocksPerTipsetLastFinality;

        public NodeChainStatus() {
        }

        public BigDecimal getBlocksPerTipsetLast100() {
            return blocksPerTipsetLast100;
        }

        public void setBlocksPerTipsetLast100(BigDecimal blocksPerTipsetLast100) {
            this.blocksPerTipsetLast100 = blocksPerTipsetLast100;
        }

        public BigDecimal getBlocksPerTipsetLastFinality() {
            return blocksPerTipsetLastFinality;
        }

        public void setBlocksPerTipsetLastFinality(BigDecimal blocksPerTipsetLastFinality) {
            this.blocksPerTipsetLastFinality = blocksPerTipsetLastFinality;
        }
    }

}
