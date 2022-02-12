package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class IpldObject extends Response<IpldObject.Ipld> {

    @Override
    public Ipld getResult() {
        return super.getResult();
    }

    public static class Ipld implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid cid;
        private String obj;

        public Ipld() {
        }

        public Cid getCid() {
            return cid;
        }

        public void setCid(Cid cid) {
            this.cid = cid;
        }

        public String getObj() {
            return obj;
        }

        public void setObj(String obj) {
            this.obj = obj;
        }
    }

}
