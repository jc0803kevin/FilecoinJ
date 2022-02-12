package io.filecoin.protocol.domain.types;

import io.filecoin.crypto.types.Message;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class MessagePrototype implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Message message;
    private Boolean validNonce;

    public MessagePrototype() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Boolean getValidNonce() {
        return validNonce;
    }

    public void setValidNonce(Boolean validNonce) {
        this.validNonce = validNonce;
    }
}
