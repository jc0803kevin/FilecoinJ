package io.filecoin.protocol.domain.builtin.miner;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;

public class SectorPreCommitInfo implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long sealProof;
    private Long sectorNumber;
    private Cid sealedCID;              // `checked:"true"` // CommR
    private Long sealRandEpoch;
    private List<Long> dealIDs;
    private Long expiration;
    private Boolean replaceCapacity;    // Whether to replace a "committed capacity" no-deal sector (requires non-empty DealIDs)
    // The committed capacity sector to replace, and it's deadline/partition location
    private Long replaceSectorDeadline;
    private Long replaceSectorPartition;
    private Long replaceSectorNumber;

    public SectorPreCommitInfo() {
    }

    public Long getSealProof() {
        return sealProof;
    }

    public void setSealProof(Long sealProof) {
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

    public Long getSealRandEpoch() {
        return sealRandEpoch;
    }

    public void setSealRandEpoch(Long sealRandEpoch) {
        this.sealRandEpoch = sealRandEpoch;
    }

    public List<Long> getDealIDs() {
        return dealIDs;
    }

    public void setDealIDs(List<Long> dealIDs) {
        this.dealIDs = dealIDs;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Boolean getReplaceCapacity() {
        return replaceCapacity;
    }

    public void setReplaceCapacity(Boolean replaceCapacity) {
        this.replaceCapacity = replaceCapacity;
    }

    public Long getReplaceSectorDeadline() {
        return replaceSectorDeadline;
    }

    public void setReplaceSectorDeadline(Long replaceSectorDeadline) {
        this.replaceSectorDeadline = replaceSectorDeadline;
    }

    public Long getReplaceSectorPartition() {
        return replaceSectorPartition;
    }

    public void setReplaceSectorPartition(Long replaceSectorPartition) {
        this.replaceSectorPartition = replaceSectorPartition;
    }

    public Long getReplaceSectorNumber() {
        return replaceSectorNumber;
    }

    public void setReplaceSectorNumber(Long replaceSectorNumber) {
        this.replaceSectorNumber = replaceSectorNumber;
    }
}
