package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class StateMinerSectorCount extends Response<StateMinerSectorCount.MinerSectors> {

    @Override
    public MinerSectors getResult() {
        return super.getResult();
    }

    public static class MinerSectors implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        // Live sectors that should be proven.
        private Long live;
        // Sectors actively contributing to power.
        private Long active;
        // Sectors with failed proofs.
        private Long faulty;

        public MinerSectors() {
        }

        public Long getLive() {
            return live;
        }

        public void setLive(Long live) {
            this.live = live;
        }

        public Long getActive() {
            return active;
        }

        public void setActive(Long active) {
            this.active = active;
        }

        public Long getFaulty() {
            return faulty;
        }

        public void setFaulty(Long faulty) {
            this.faulty = faulty;
        }
    }
}
