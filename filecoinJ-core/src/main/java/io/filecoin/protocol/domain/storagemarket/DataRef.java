package io.filecoin.protocol.domain.storagemarket;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;

public class DataRef implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private String transferType;
    private Cid root;
    private Cid pieceCid;
    private Long pieceSize;
    private Long rawBlockSize;

    public DataRef() {
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public Cid getRoot() {
        return root;
    }

    public void setRoot(Cid root) {
        this.root = root;
    }

    public Cid getPieceCid() {
        return pieceCid;
    }

    public void setPieceCid(Cid pieceCid) {
        this.pieceCid = pieceCid;
    }

    public Long getPieceSize() {
        return pieceSize;
    }

    public void setPieceSize(Long pieceSize) {
        this.pieceSize = pieceSize;
    }

    public Long getRawBlockSize() {
        return rawBlockSize;
    }

    public void setRawBlockSize(Long rawBlockSize) {
        this.rawBlockSize = rawBlockSize;
    }
}
