package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class ClientListImports extends Response<List<ClientListImports.Import>> {

    @Override
    public List<Import> getResult() {
        return super.getResult();
    }

    public static class Import implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long key;
        private String err;
        private Cid root;
        private String source;
        private String filePath;
        private String carPath;

        public Import() {
        }

        public Long getKey() {
            return key;
        }

        public void setKey(Long key) {
            this.key = key;
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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getCarPath() {
            return carPath;
        }

        public void setCarPath(String carPath) {
            this.carPath = carPath;
        }
    }
}
