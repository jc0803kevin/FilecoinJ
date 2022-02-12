package io.filecoin.protocol.domain.types;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class KeyInfo implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    //@JsonProperty("Type")
    private String type;
    private String privateKey;

    public KeyInfo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
