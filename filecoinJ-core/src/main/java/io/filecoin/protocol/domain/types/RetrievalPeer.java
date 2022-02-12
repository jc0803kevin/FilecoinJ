package io.filecoin.protocol.domain.types;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class RetrievalPeer implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String address;
    private String iD; // optional
    private Cid pieceCID;

    public RetrievalPeer() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public Cid getPieceCID() {
        return pieceCID;
    }

    public void setPieceCID(Cid pieceCID) {
        this.pieceCID = pieceCID;
    }
}
