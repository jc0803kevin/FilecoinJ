package io.filecoin.protocol.domain;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.types.RetrievalPeer;

import java.io.Serializable;
import java.math.BigInteger;

public class QueryOffer implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String err;
    private Cid root;
    private Cid piece;
    private Long size;
    private BigInteger minPrice;
    private BigInteger unsealPrice;
    private Long paymentInterval;
    private Long paymentIntervalIncrease;
    private String miner;
    private RetrievalPeer minerPeer;

    public QueryOffer() {
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
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

    public BigInteger getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigInteger minPrice) {
        this.minPrice = minPrice;
    }

    public BigInteger getUnsealPrice() {
        return unsealPrice;
    }

    public void setUnsealPrice(BigInteger unsealPrice) {
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
