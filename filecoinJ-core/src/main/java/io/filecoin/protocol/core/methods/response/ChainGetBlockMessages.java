package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class ChainGetBlockMessages extends Response<ChainGetBlockMessages.BlockMessages> {

    @Override
    public BlockMessages getResult() {
        return super.getResult();
    }

    public class BlockMessages implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private List<Message> blsMessages;
        private List<SignedMessage> secpkMessages;
        private List<Cid> cids;

        public BlockMessages() {
        }

        public List<Message> getBlsMessages() {
            return blsMessages;
        }

        public void setBlsMessages(List<Message> blsMessages) {
            this.blsMessages = blsMessages;
        }

        public List<SignedMessage> getSecpkMessages() {
            return secpkMessages;
        }

        public void setSecpkMessages(List<SignedMessage> secpkMessages) {
            this.secpkMessages = secpkMessages;
        }

        public List<Cid> getCids() {
            return cids;
        }

        public void setCids(List<Cid> cids) {
            this.cids = cids;
        }
    }
}
