package io.filecoin.protocol.core.methods.request;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.math.BigInteger;

public class MarketDeal implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private DealProposal proposal;
    private DealState state;

    public MarketDeal() {
    }

    public DealProposal getProposal() {
        return proposal;
    }

    public void setProposal(DealProposal proposal) {
        this.proposal = proposal;
    }

    public DealState getState() {
        return state;
    }

    public void setState(DealState state) {
        this.state = state;
    }

    public static class DealProposal implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;
        private Cid pieceCID;
        private Long pieceSize;
        private Boolean verifiedDeal;
        private String client;
        private String provider;
        private String label;
        private Long startEpoch;
        private Long endEpoch;
        private BigInteger storagePricePerEpoch;
        private BigInteger providerCollateral;
        private BigInteger clientCollateral;

        public DealProposal() {
        }

        public Cid getPieceCID() {
            return pieceCID;
        }

        public void setPieceCID(Cid pieceCID) {
            this.pieceCID = pieceCID;
        }

        public Long getPieceSize() {
            return pieceSize;
        }

        public void setPieceSize(Long pieceSize) {
            this.pieceSize = pieceSize;
        }

        public Boolean getVerifiedDeal() {
            return verifiedDeal;
        }

        public void setVerifiedDeal(Boolean verifiedDeal) {
            this.verifiedDeal = verifiedDeal;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Long getStartEpoch() {
            return startEpoch;
        }

        public void setStartEpoch(Long startEpoch) {
            this.startEpoch = startEpoch;
        }

        public Long getEndEpoch() {
            return endEpoch;
        }

        public void setEndEpoch(Long endEpoch) {
            this.endEpoch = endEpoch;
        }

        public BigInteger getStoragePricePerEpoch() {
            return storagePricePerEpoch;
        }

        public void setStoragePricePerEpoch(BigInteger storagePricePerEpoch) {
            this.storagePricePerEpoch = storagePricePerEpoch;
        }

        public BigInteger getProviderCollateral() {
            return providerCollateral;
        }

        public void setProviderCollateral(BigInteger providerCollateral) {
            this.providerCollateral = providerCollateral;
        }

        public BigInteger getClientCollateral() {
            return clientCollateral;
        }

        public void setClientCollateral(BigInteger clientCollateral) {
            this.clientCollateral = clientCollateral;
        }
    }

    public static class DealState implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;
        private Long sectorStartEpoch; // -1 if not yet included in proven sector
        private Long lastUpdatedEpoch; // -1 if deal state never updated
        private Long slashEpoch;       // -1 if deal never slashed

        public DealState() {
        }

        public Long getSectorStartEpoch() {
            return sectorStartEpoch;
        }

        public void setSectorStartEpoch(Long sectorStartEpoch) {
            this.sectorStartEpoch = sectorStartEpoch;
        }

        public Long getLastUpdatedEpoch() {
            return lastUpdatedEpoch;
        }

        public void setLastUpdatedEpoch(Long lastUpdatedEpoch) {
            this.lastUpdatedEpoch = lastUpdatedEpoch;
        }

        public Long getSlashEpoch() {
            return slashEpoch;
        }

        public void setSlashEpoch(Long slashEpoch) {
            this.slashEpoch = slashEpoch;
        }
    }
}
