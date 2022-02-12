package io.filecoin.crypto.types;

import java.io.Serializable;

public class Signature implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer type;
    private String data;

    public Signature() {
    }

    public Signature(Integer type, String data) {
        this.type = type;
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
