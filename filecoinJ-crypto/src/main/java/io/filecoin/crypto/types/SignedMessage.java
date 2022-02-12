package io.filecoin.crypto.types;

import java.io.Serializable;


public class SignedMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Message message;

    private Signature signature;

    private Cid cid;

    public SignedMessage() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }
}
