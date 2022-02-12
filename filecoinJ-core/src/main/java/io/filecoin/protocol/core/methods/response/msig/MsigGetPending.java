package io.filecoin.protocol.core.methods.response.msig;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class MsigGetPending extends Response<List<MsigGetPending.MsigTransaction>> {

    @Override
    public List<MsigTransaction> getResult() {
        return super.getResult();
    }

    public static class MsigTransaction implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long iD;
        private String to;
        private BigInteger value;
        private Integer method;
        private String params;
        private List<String> approved;

        public MsigTransaction() {
        }

        public Long getiD() {
            return iD;
        }

        public void setiD(Long iD) {
            this.iD = iD;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigInteger getValue() {
            return value;
        }

        public void setValue(BigInteger value) {
            this.value = value;
        }

        public Integer getMethod() {
            return method;
        }

        public void setMethod(Integer method) {
            this.method = method;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public List<String> getApproved() {
            return approved;
        }

        public void setApproved(List<String> approved) {
            this.approved = approved;
        }
    }
}
