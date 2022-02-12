package io.filecoin.protocol.domain.storagemarket;

import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;

public class DealStage implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private List<Stages> stages;

    public static class Stages implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String name;
        private String description;
        private String expectedDuration;
        private String createdTime;
        private String updatedTime;

        private List<Log> Logs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getExpectedDuration() {
            return expectedDuration;
        }

        public void setExpectedDuration(String expectedDuration) {
            this.expectedDuration = expectedDuration;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public List<Log> getLogs() {
            return Logs;
        }

        public void setLogs(List<Log> logs) {
            Logs = logs;
        }
    }

    public static class Log implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String log;
        private String updatedTime;

        public Log() {
        }

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }
    }


    public DealStage() {
    }

    public List<Stages> getStages() {
        return stages;
    }

    public void setStages(List<Stages> stages) {
        this.stages = stages;
    }
}


