package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class ClientDealSize extends Response<ClientDealSize.DataSize> {

    @Override
    public DataSize getResult() {
        return super.getResult();
    }

    public static class DataSize implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long payloadSize;
        private Long pieceSize;

        public DataSize() {
        }

        public Long getPayloadSize() {
            return payloadSize;
        }

        public void setPayloadSize(Long payloadSize) {
            this.payloadSize = payloadSize;
        }

        public Long getPieceSize() {
            return pieceSize;
        }

        public void setPieceSize(Long pieceSize) {
            this.pieceSize = pieceSize;
        }
    }
}
