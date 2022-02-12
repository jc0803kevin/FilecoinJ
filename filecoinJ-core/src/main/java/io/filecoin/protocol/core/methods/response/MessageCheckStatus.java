package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class MessageCheckStatus extends Response<List<List<MessageCheckStatus.Status>>> {

    @Override
    public List<List<Status>> getResult() {
        return super.getResult();
    }

    public static class Status implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid cid;
        private CheckStatus checkStatus;

        public Status() {
        }

        public Cid getCid() {
            return cid;
        }

        public void setCid(Cid cid) {
            this.cid = cid;
        }

        public CheckStatus getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(CheckStatus checkStatus) {
            this.checkStatus = checkStatus;
        }
    }

    public static class CheckStatus implements Serializable{
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Integer code;
        private Boolean ok;
        private String err;
        private String hint;

        public CheckStatus() {
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Boolean getOk() {
            return ok;
        }

        public void setOk(Boolean ok) {
            this.ok = ok;
        }

        public String getErr() {
            return err;
        }

        public void setErr(String err) {
            this.err = err;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }
    }
}
