package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class StateSectorExpiration extends Response<StateSectorExpiration.SectorExpiration> {

    @Override
    public SectorExpiration getResult() {
        return super.getResult();
    }

    public static class SectorExpiration implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long onTime;
        private Long early;

        public SectorExpiration() {
        }

        public Long getOnTime() {
            return onTime;
        }

        public void setOnTime(Long onTime) {
            this.onTime = onTime;
        }

        public Long getEarly() {
            return early;
        }

        public void setEarly(Long early) {
            this.early = early;
        }
    }

}
