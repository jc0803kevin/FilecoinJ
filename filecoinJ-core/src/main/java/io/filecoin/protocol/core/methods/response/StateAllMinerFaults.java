package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.util.List;

public class StateAllMinerFaults extends Response<List<StateAllMinerFaults.Fault>> {

    @Override
    public List<Fault> getResult() {
        return super.getResult();
    }

    public static class Fault implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String miner;
        private Long epoch;

        public Fault() {
        }

        public String getMiner() {
            return miner;
        }

        public void setMiner(String miner) {
            this.miner = miner;
        }

        public Long getEpoch() {
            return epoch;
        }

        public void setEpoch(Long epoch) {
            this.epoch = epoch;
        }
    }
}
