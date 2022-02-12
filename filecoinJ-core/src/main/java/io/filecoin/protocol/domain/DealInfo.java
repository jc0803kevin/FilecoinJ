package io.filecoin.protocol.domain;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.storagemarket.DataRef;
import io.filecoin.protocol.domain.storagemarket.DealStage;
import io.filecoin.protocol.domain.types.ChannelID;
import io.filecoin.protocol.domain.types.DataTransferChannel;

import java.io.Serializable;
import java.math.BigInteger;

public class DealInfo implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Cid proposalCid;
    private Long state;
    private String message; // more information about deal state, particularly errors
    private DealStage dealStages;
    private String provider;
    private DataRef dataRef;
    private Cid pieceCID;
    private Long size;
    private BigInteger pricePerEpoch;
    private Long duration;
    private Long dealID;
    private String creationTime;
    private Boolean verified;
    private ChannelID transferChannelID;
    private DataTransferChannel dataTransfer;

    public DealInfo() {
    }

    public Cid getProposalCid() {
        return proposalCid;
    }

    public void setProposalCid(Cid proposalCid) {
        this.proposalCid = proposalCid;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DealStage getDealStages() {
        return dealStages;
    }

    public void setDealStages(DealStage dealStages) {
        this.dealStages = dealStages;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public DataRef getDataRef() {
        return dataRef;
    }

    public void setDataRef(DataRef dataRef) {
        this.dataRef = dataRef;
    }

    public Cid getPieceCID() {
        return pieceCID;
    }

    public void setPieceCID(Cid pieceCID) {
        this.pieceCID = pieceCID;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public BigInteger getPricePerEpoch() {
        return pricePerEpoch;
    }

    public void setPricePerEpoch(BigInteger pricePerEpoch) {
        this.pricePerEpoch = pricePerEpoch;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDealID() {
        return dealID;
    }

    public void setDealID(Long dealID) {
        this.dealID = dealID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public ChannelID getTransferChannelID() {
        return transferChannelID;
    }

    public void setTransferChannelID(ChannelID transferChannelID) {
        this.transferChannelID = transferChannelID;
    }

    public DataTransferChannel getDataTransfer() {
        return dataTransfer;
    }

    public void setDataTransfer(DataTransferChannel dataTransfer) {
        this.dataTransfer = dataTransfer;
    }
}
