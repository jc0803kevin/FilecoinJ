package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class ClientCalcCommP extends Response<ClientCalcCommP.CommPRet> {

    @Override
    public CommPRet getResult() {
        return super.getResult();
    }

    public static class CommPRet implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid root;
        private Long size;

        public CommPRet() {
        }

        public Cid getRoot() {
            return root;
        }

        public void setRoot(Cid root) {
            this.root = root;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }
    }
}
