package io.filecoin.crypto.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Cid implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("/")
    private String str;

    public static Cid of(String str) {
        Cid result = new Cid();
        result.str = str;
        return result;
    }

    public Cid() {
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
