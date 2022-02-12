package io.filecoin.protocol.core.methods.request;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class MessageMatch implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String to;
    private String from;

    public MessageMatch() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
