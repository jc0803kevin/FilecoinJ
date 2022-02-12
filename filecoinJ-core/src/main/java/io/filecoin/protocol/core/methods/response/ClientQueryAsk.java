package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class ClientQueryAsk extends Response<ClientQueryAsk.StorageAsk> {
    @Override
    public StorageAsk getResult() {
        return super.getResult();
    }

    public static class StorageAsk implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        // Price per GiB / Epoch
        private BigInteger price;
        private BigInteger verifiedPrice;
        private Long minPieceSize;
        private Long maxPieceSize;
        private String miner;
        private Long timestamp;
        private Long expiry;
        private Long seqNo;

        public StorageAsk() {
        }

        public BigInteger getPrice() {
            return price;
        }

        public void setPrice(BigInteger price) {
            this.price = price;
        }

        public BigInteger getVerifiedPrice() {
            return verifiedPrice;
        }

        public void setVerifiedPrice(BigInteger verifiedPrice) {
            this.verifiedPrice = verifiedPrice;
        }

        public Long getMinPieceSize() {
            return minPieceSize;
        }

        public void setMinPieceSize(Long minPieceSize) {
            this.minPieceSize = minPieceSize;
        }

        public Long getMaxPieceSize() {
            return maxPieceSize;
        }

        public void setMaxPieceSize(Long maxPieceSize) {
            this.maxPieceSize = maxPieceSize;
        }

        public String getMiner() {
            return miner;
        }

        public void setMiner(String miner) {
            this.miner = miner;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public Long getExpiry() {
            return expiry;
        }

        public void setExpiry(Long expiry) {
            this.expiry = expiry;
        }

        public Long getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(Long seqNo) {
            this.seqNo = seqNo;
        }
    }
}
