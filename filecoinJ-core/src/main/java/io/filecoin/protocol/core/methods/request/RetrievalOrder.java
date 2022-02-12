package io.filecoin.protocol.core.methods.request;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.types.RetrievalPeer;

import java.io.Serializable;

public class RetrievalOrder implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    // make this less unixfs specific
    private Cid root;
    private Cid piece;
    private Long size;
    private Long localStore; // if specified, get data from local store
    // support offset
    private String total;
    private String unsealPrice;
    private Long paymentInterval;
    private Long paymentIntervalIncrease;
    private String client;
    private String miner;
    private RetrievalPeer minerPeer;

    public RetrievalOrder() {
    }

    public Cid getRoot() {
        return root;
    }

    public void setRoot(Cid root) {
        this.root = root;
    }

    public Cid getPiece() {
        return piece;
    }

    public void setPiece(Cid piece) {
        this.piece = piece;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getLocalStore() {
        return localStore;
    }

    public void setLocalStore(Long localStore) {
        this.localStore = localStore;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUnsealPrice() {
        return unsealPrice;
    }

    public void setUnsealPrice(String unsealPrice) {
        this.unsealPrice = unsealPrice;
    }

    public Long getPaymentInterval() {
        return paymentInterval;
    }

    public void setPaymentInterval(Long paymentInterval) {
        this.paymentInterval = paymentInterval;
    }

    public Long getPaymentIntervalIncrease() {
        return paymentIntervalIncrease;
    }

    public void setPaymentIntervalIncrease(Long paymentIntervalIncrease) {
        this.paymentIntervalIncrease = paymentIntervalIncrease;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public RetrievalPeer getMinerPeer() {
        return minerPeer;
    }

    public void setMinerPeer(RetrievalPeer minerPeer) {
        this.minerPeer = minerPeer;
    }
}
