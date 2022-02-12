package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class ClientDealPieceCID extends Response<ClientDealPieceCID.DataCIDSize> {

    @Override
    public DataCIDSize getResult() {
        return super.getResult();
    }

    public class DataCIDSize implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long payloadSize;
        private Long pieceSize;
        private Cid pieceCID;

        public DataCIDSize() {
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

        public Cid getPieceCID() {
            return pieceCID;
        }

        public void setPieceCID(Cid pieceCID) {
            this.pieceCID = pieceCID;
        }
    }
}
