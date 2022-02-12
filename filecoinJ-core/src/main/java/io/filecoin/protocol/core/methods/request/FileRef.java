package io.filecoin.protocol.core.methods.request;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class FileRef implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String path;
    private Boolean isCAR;

    public FileRef() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getCAR() {
        return isCAR;
    }

    public void setCAR(Boolean CAR) {
        isCAR = CAR;
    }
}
