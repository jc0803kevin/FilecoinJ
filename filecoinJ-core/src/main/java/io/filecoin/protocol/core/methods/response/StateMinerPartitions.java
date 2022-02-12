package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class StateMinerPartitions extends Response<StateMinerPartitions.Partition> {

    @Override
    public Partition getResult() {
        return super.getResult();
    }

    public static class Partition implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String allSectors;
        private String faultySectors;
        private String recoveringSectors;
        private String liveSectors;
        private String activeSectors;

        public Partition() {
        }

        public String getAllSectors() {
            return allSectors;
        }

        public void setAllSectors(String allSectors) {
            this.allSectors = allSectors;
        }

        public String getFaultySectors() {
            return faultySectors;
        }

        public void setFaultySectors(String faultySectors) {
            this.faultySectors = faultySectors;
        }

        public String getRecoveringSectors() {
            return recoveringSectors;
        }

        public void setRecoveringSectors(String recoveringSectors) {
            this.recoveringSectors = recoveringSectors;
        }

        public String getLiveSectors() {
            return liveSectors;
        }

        public void setLiveSectors(String liveSectors) {
            this.liveSectors = liveSectors;
        }

        public String getActiveSectors() {
            return activeSectors;
        }

        public void setActiveSectors(String activeSectors) {
            this.activeSectors = activeSectors;
        }
    }

}
