package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.types.TipSet;

import java.io.Serializable;
import java.util.List;

public class ChainGetPath extends Response<List<ChainGetPath.HeadChange>> {

    @Override
    public List<HeadChange> getResult() {
        return super.getResult();
    }

    public static class HeadChange implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String type;
        private TipSet val;

        public HeadChange() {
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public TipSet getVal() {
            return val;
        }

        public void setVal(TipSet val) {
            this.val = val;
        }
    }
}
