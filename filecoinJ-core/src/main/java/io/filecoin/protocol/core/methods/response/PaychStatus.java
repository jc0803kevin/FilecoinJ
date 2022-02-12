package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class PaychStatus extends Response<PaychStatus.Status> {

    @Override
    public Status getResult() {
        return super.getResult();
    }

    public static class Status implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String controlAddr;
        private Integer direction;

        public Status() {
        }

        public String getControlAddr() {
            return controlAddr;
        }

        public void setControlAddr(String controlAddr) {
            this.controlAddr = controlAddr;
        }

        public Integer getDirection() {
            return direction;
        }

        public void setDirection(Integer direction) {
            this.direction = direction;
        }
    }
}
