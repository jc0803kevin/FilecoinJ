package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class ClientImport extends Response<ClientImport.ImportRes> {

    @Override
    public ImportRes getResult() {
        return super.getResult();
    }

    public static class ImportRes implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid root;
        private Long importID;

        public ImportRes() {
        }

        public Cid getRoot() {
            return root;
        }

        public void setRoot(Cid root) {
            this.root = root;
        }

        public Long getImportID() {
            return importID;
        }

        public void setImportID(Long importID) {
            this.importID = importID;
        }
    }

}
