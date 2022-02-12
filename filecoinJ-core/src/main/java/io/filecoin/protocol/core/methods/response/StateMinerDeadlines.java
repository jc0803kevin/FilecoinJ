package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class StateMinerDeadlines extends Response<List<StateMinerDeadlines.Deadline>> {

    @Override
    public List<Deadline> getResult() {
        return super.getResult();
    }

    public static class Deadline implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        //private String postSubmissions;
        private Long disputableProofCount;

        public Deadline() {
        }

        public Long getDisputableProofCount() {
            return disputableProofCount;
        }

        public void setDisputableProofCount(Long disputableProofCount) {
            this.disputableProofCount = disputableProofCount;
        }
    }
}
