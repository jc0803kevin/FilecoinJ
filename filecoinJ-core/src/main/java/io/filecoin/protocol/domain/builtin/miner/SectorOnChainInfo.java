package io.filecoin.protocol.domain.builtin.miner;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class SectorOnChainInfo implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long sectorNumber;
    private Long sealProof;
    private Cid sealedCID;
    private List<Long> dealIDs;
    private Long activation;
    private Long expiration;
    private Long dealWeight;
    private Long verifiedDealWeight;
    private BigInteger initialPledge;
    private BigInteger expectedDayReward;
    private BigInteger expectedStoragePledge;

    public SectorOnChainInfo() {
    }

    public Long getSectorNumber() {
        return sectorNumber;
    }

    public void setSectorNumber(Long sectorNumber) {
        this.sectorNumber = sectorNumber;
    }

    public Long getSealProof() {
        return sealProof;
    }

    public void setSealProof(Long sealProof) {
        this.sealProof = sealProof;
    }

    public Cid getSealedCID() {
        return sealedCID;
    }

    public void setSealedCID(Cid sealedCID) {
        this.sealedCID = sealedCID;
    }

    public List<Long> getDealIDs() {
        return dealIDs;
    }

    public void setDealIDs(List<Long> dealIDs) {
        this.dealIDs = dealIDs;
    }

    public Long getActivation() {
        return activation;
    }

    public void setActivation(Long activation) {
        this.activation = activation;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Long getDealWeight() {
        return dealWeight;
    }

    public void setDealWeight(Long dealWeight) {
        this.dealWeight = dealWeight;
    }

    public Long getVerifiedDealWeight() {
        return verifiedDealWeight;
    }

    public void setVerifiedDealWeight(Long verifiedDealWeight) {
        this.verifiedDealWeight = verifiedDealWeight;
    }

    public BigInteger getInitialPledge() {
        return initialPledge;
    }

    public void setInitialPledge(BigInteger initialPledge) {
        this.initialPledge = initialPledge;
    }

    public BigInteger getExpectedDayReward() {
        return expectedDayReward;
    }

    public void setExpectedDayReward(BigInteger expectedDayReward) {
        this.expectedDayReward = expectedDayReward;
    }

    public BigInteger getExpectedStoragePledge() {
        return expectedStoragePledge;
    }

    public void setExpectedStoragePledge(BigInteger expectedStoragePledge) {
        this.expectedStoragePledge = expectedStoragePledge;
    }
}
