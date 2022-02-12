package io.filecoin.protocol.domain.types;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.storagemarket.DealStage;

import java.io.Serializable;
import java.util.List;

public class DataTransferChannel implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long transferID;
    private Long status;
    private Cid baseCID;
    private Boolean isInitiator;
    private Boolean isSender;
    private String voucher;
    private String message;
    private String otherPeer;
    private Long transferred;
    private List<DealStage> stages;

    public DataTransferChannel() {
    }

    public Long getTransferID() {
        return transferID;
    }

    public void setTransferID(Long transferID) {
        this.transferID = transferID;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Cid getBaseCID() {
        return baseCID;
    }

    public void setBaseCID(Cid baseCID) {
        this.baseCID = baseCID;
    }

    public Boolean getInitiator() {
        return isInitiator;
    }

    public void setInitiator(Boolean initiator) {
        isInitiator = initiator;
    }

    public Boolean getSender() {
        return isSender;
    }

    public void setSender(Boolean sender) {
        isSender = sender;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherPeer() {
        return otherPeer;
    }

    public void setOtherPeer(String otherPeer) {
        this.otherPeer = otherPeer;
    }

    public Long getTransferred() {
        return transferred;
    }

    public void setTransferred(Long transferred) {
        this.transferred = transferred;
    }

    public List<DealStage> getStages() {
        return stages;
    }

    public void setStages(List<DealStage> stages) {
        this.stages = stages;
    }
}
