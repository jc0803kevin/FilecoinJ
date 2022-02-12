package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class StateMinerProvingDeadline extends Response<StateMinerProvingDeadline.Info> {

    @Override
    public Info getResult() {
        return super.getResult();
    }

    public static class Info implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        // Deadline parameters
        private Long currentEpoch;      // Epoch at which this info was calculated.
        private Long periodStart;       // First epoch of the proving period (<= CurrentEpoch).
        private Long index;             // A deadline index, in [0..d.WPoStProvingPeriodDeadlines) unless period elapsed.
        private Long open;              // First epoch from which a proof may be submitted (>= CurrentEpoch).
        private Long close;             // First epoch from which a proof may no longer be submitted (>= Open).
        private Long challenge;         // Epoch at which to sample the chain for challenge (< Open).
        private Long faultCutoff;       // First epoch at which a fault declaration is rejected (< Open).
        // Protocol parameters
        private Long wPoStPeriodDeadlines;
        private Long wPoStProvingPeriod; // the number of epochs in a window post proving period
        private Long wPoStChallengeWindow;
        private Long wPoStChallengeLookback;
        private Long faultDeclarationCutoff;

        public Info() {
        }

        public Long getCurrentEpoch() {
            return currentEpoch;
        }

        public void setCurrentEpoch(Long currentEpoch) {
            this.currentEpoch = currentEpoch;
        }

        public Long getPeriodStart() {
            return periodStart;
        }

        public void setPeriodStart(Long periodStart) {
            this.periodStart = periodStart;
        }

        public Long getIndex() {
            return index;
        }

        public void setIndex(Long index) {
            this.index = index;
        }

        public Long getOpen() {
            return open;
        }

        public void setOpen(Long open) {
            this.open = open;
        }

        public Long getClose() {
            return close;
        }

        public void setClose(Long close) {
            this.close = close;
        }

        public Long getChallenge() {
            return challenge;
        }

        public void setChallenge(Long challenge) {
            this.challenge = challenge;
        }

        public Long getFaultCutoff() {
            return faultCutoff;
        }

        public void setFaultCutoff(Long faultCutoff) {
            this.faultCutoff = faultCutoff;
        }

        public Long getwPoStPeriodDeadlines() {
            return wPoStPeriodDeadlines;
        }

        public void setwPoStPeriodDeadlines(Long wPoStPeriodDeadlines) {
            this.wPoStPeriodDeadlines = wPoStPeriodDeadlines;
        }

        public Long getwPoStProvingPeriod() {
            return wPoStProvingPeriod;
        }

        public void setwPoStProvingPeriod(Long wPoStProvingPeriod) {
            this.wPoStProvingPeriod = wPoStProvingPeriod;
        }

        public Long getwPoStChallengeWindow() {
            return wPoStChallengeWindow;
        }

        public void setwPoStChallengeWindow(Long wPoStChallengeWindow) {
            this.wPoStChallengeWindow = wPoStChallengeWindow;
        }

        public Long getwPoStChallengeLookback() {
            return wPoStChallengeLookback;
        }

        public void setwPoStChallengeLookback(Long wPoStChallengeLookback) {
            this.wPoStChallengeLookback = wPoStChallengeLookback;
        }

        public Long getFaultDeclarationCutoff() {
            return faultDeclarationCutoff;
        }

        public void setFaultDeclarationCutoff(Long faultDeclarationCutoff) {
            this.faultDeclarationCutoff = faultDeclarationCutoff;
        }
    }

}
