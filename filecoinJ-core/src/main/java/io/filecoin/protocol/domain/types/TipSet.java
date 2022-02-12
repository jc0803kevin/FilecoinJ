package io.filecoin.protocol.domain.types;


import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;


public class TipSet implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private List<Cid> cids;
    private List<BlockHeader> blocks;
    private Long height;


    public TipSet() {
    }

    public List<Cid> getCids() {
        return cids;
    }

    public void setCids(List<Cid> cids) {
        this.cids = cids;
    }

    public List<BlockHeader> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockHeader> blocks) {
        this.blocks = blocks;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
