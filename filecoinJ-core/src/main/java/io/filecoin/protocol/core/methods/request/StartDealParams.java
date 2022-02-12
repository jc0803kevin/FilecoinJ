package io.filecoin.protocol.core.methods.request;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.storagemarket.DataRef;

import java.io.Serializable;

public class StartDealParams implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private DataRef data;
    private String wallet;
    private String miner;
    private String epochPrice;
    private Long minBlocksDuration;
    private String providerCollateral;
    private Long dealStartEpoch;
    private Boolean fastRetrieval;
    private Boolean verifiedDeal;

    public StartDealParams() {
    }

    public DataRef getData() {
        return data;
    }

    public void setData(DataRef data) {
        this.data = data;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public String getEpochPrice() {
        return epochPrice;
    }

    public void setEpochPrice(String epochPrice) {
        this.epochPrice = epochPrice;
    }

    public Long getMinBlocksDuration() {
        return minBlocksDuration;
    }

    public void setMinBlocksDuration(Long minBlocksDuration) {
        this.minBlocksDuration = minBlocksDuration;
    }

    public String getProviderCollateral() {
        return providerCollateral;
    }

    public void setProviderCollateral(String providerCollateral) {
        this.providerCollateral = providerCollateral;
    }

    public Long getDealStartEpoch() {
        return dealStartEpoch;
    }

    public void setDealStartEpoch(Long dealStartEpoch) {
        this.dealStartEpoch = dealStartEpoch;
    }

    public Boolean getFastRetrieval() {
        return fastRetrieval;
    }

    public void setFastRetrieval(Boolean fastRetrieval) {
        this.fastRetrieval = fastRetrieval;
    }

    public Boolean getVerifiedDeal() {
        return verifiedDeal;
    }

    public void setVerifiedDeal(Boolean verifiedDeal) {
        this.verifiedDeal = verifiedDeal;
    }
}
