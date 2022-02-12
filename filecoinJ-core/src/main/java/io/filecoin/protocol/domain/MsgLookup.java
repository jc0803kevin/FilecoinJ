package io.filecoin.protocol.domain;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.types.MessageReceipt;
import io.filecoin.protocol.domain.types.TipSetKey;

import java.io.Serializable;

public class MsgLookup implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Cid message;            // Can be different than requested, in case it was replaced, but only gas values changed
    private MessageReceipt receipt;
    private String returnDec;       //interface{}
    private TipSetKey tipSet;
    private Long height;

    public MsgLookup() {
    }

    public Cid getMessage() {
        return message;
    }

    public void setMessage(Cid message) {
        this.message = message;
    }

    public MessageReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(MessageReceipt receipt) {
        this.receipt = receipt;
    }

    public String getReturnDec() {
        return returnDec;
    }

    public void setReturnDec(String returnDec) {
        this.returnDec = returnDec;
    }

    public TipSetKey getTipSet() {
        return tipSet;
    }

    public void setTipSet(TipSetKey tipSet) {
        this.tipSet = tipSet;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
