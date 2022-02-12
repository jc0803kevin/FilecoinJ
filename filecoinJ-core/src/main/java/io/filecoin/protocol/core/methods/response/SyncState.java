package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.SyncStateStage;
import io.filecoin.protocol.domain.types.TipSet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SyncState extends Response<SyncState.State> {

    @Override
    public State getResult() {
        return super.getResult();
    }

    public static class State implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private List<ActiveSyncs> activeSyncs;

        private Long vMApplied;

        public State() {
        }

        public List<ActiveSyncs> getActiveSyncs() {
            return activeSyncs;
        }

        public void setActiveSyncs(List<ActiveSyncs> activeSyncs) {
            this.activeSyncs = activeSyncs;
        }

        public Long getvMApplied() {
            return vMApplied;
        }

        public void setvMApplied(Long vMApplied) {
            this.vMApplied = vMApplied;
        }
    }

    public static class ActiveSyncs implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Long workerID;

        private TipSet base;

        private TipSet target;

        private SyncStateStage stage;

        private Long height;

        private Date start;

        private Date end;

        private String message;

        public ActiveSyncs() {
        }

        public Long getWorkerID() {
            return workerID;
        }

        public void setWorkerID(Long workerID) {
            this.workerID = workerID;
        }

        public TipSet getBase() {
            return base;
        }

        public void setBase(TipSet base) {
            this.base = base;
        }

        public TipSet getTarget() {
            return target;
        }

        public void setTarget(TipSet target) {
            this.target = target;
        }

        public SyncStateStage getStage() {
            return stage;
        }

        public void setStage(SyncStateStage stage) {
            this.stage = stage;
        }

        public Long getHeight() {
            return height;
        }

        public void setHeight(Long height) {
            this.height = height;
        }

        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
