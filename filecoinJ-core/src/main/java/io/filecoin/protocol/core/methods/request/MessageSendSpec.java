package io.filecoin.protocol.core.methods.request;


import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class MessageSendSpec implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String maxFee;

    public MessageSendSpec() {
    }

    public String getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(String maxFee) {
        this.maxFee = maxFee;
    }
}
