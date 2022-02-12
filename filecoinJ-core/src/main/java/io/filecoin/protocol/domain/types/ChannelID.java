package io.filecoin.protocol.domain.types;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class ChannelID implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String initiator;
    private String responder;
    private Long iD;

    public ChannelID() {
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
    }
}
