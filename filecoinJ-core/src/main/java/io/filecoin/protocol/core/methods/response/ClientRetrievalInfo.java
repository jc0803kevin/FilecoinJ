package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.types.ChannelID;
import io.filecoin.protocol.domain.types.DataTransferChannel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class ClientRetrievalInfo extends Response<List<ClientRetrievalInfo.RetrievalInfo>> {

    @Override
    public List<RetrievalInfo> getResult() {
        return super.getResult();
    }

    public static class RetrievalInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid payloadCID;
        private Long iD;
        private Cid pieceCID;
        private BigInteger pricePerByte;
        private BigInteger unsealPrice;
        private Long status;
        private String message; // more information about deal state, particularly errors
        private String provider;
        private Long bytesReceived;
        private Long bytesPaidFor;
        private BigInteger totalPaid;
        private ChannelID transferChannelID;
        private DataTransferChannel dataTransfer;

        public RetrievalInfo() {
        }

        public Cid getPayloadCID() {
            return payloadCID;
        }

        public void setPayloadCID(Cid payloadCID) {
            this.payloadCID = payloadCID;
        }

        public Long getiD() {
            return iD;
        }

        public void setiD(Long iD) {
            this.iD = iD;
        }

        public Cid getPieceCID() {
            return pieceCID;
        }

        public void setPieceCID(Cid pieceCID) {
            this.pieceCID = pieceCID;
        }

        public BigInteger getPricePerByte() {
            return pricePerByte;
        }

        public void setPricePerByte(BigInteger pricePerByte) {
            this.pricePerByte = pricePerByte;
        }

        public BigInteger getUnsealPrice() {
            return unsealPrice;
        }

        public void setUnsealPrice(BigInteger unsealPrice) {
            this.unsealPrice = unsealPrice;
        }

        public Long getStatus() {
            return status;
        }

        public void setStatus(Long status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public Long getBytesReceived() {
            return bytesReceived;
        }

        public void setBytesReceived(Long bytesReceived) {
            this.bytesReceived = bytesReceived;
        }

        public Long getBytesPaidFor() {
            return bytesPaidFor;
        }

        public void setBytesPaidFor(Long bytesPaidFor) {
            this.bytesPaidFor = bytesPaidFor;
        }

        public BigInteger getTotalPaid() {
            return totalPaid;
        }

        public void setTotalPaid(BigInteger totalPaid) {
            this.totalPaid = totalPaid;
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
}
