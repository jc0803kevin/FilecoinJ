package io.filecoin.protocol.domain.paych;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class ModVerifyParams implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String actor;
    private Integer method;
    private String data;

    public ModVerifyParams() {
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
