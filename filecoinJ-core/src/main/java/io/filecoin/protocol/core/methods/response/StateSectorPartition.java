package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class StateSectorPartition extends Response<StateSectorPartition.SectorLocation> {

    @Override
    public SectorLocation getResult() {
        return super.getResult();
    }

    public static class SectorLocation implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long deadline;
        private Long partition;

        public SectorLocation() {
        }

        public Long getDeadline() {
            return deadline;
        }

        public void setDeadline(Long deadline) {
            this.deadline = deadline;
        }

        public Long getPartition() {
            return partition;
        }

        public void setPartition(Long partition) {
            this.partition = partition;
        }
    }
}
