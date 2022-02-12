package io.filecoin.protocol.domain;

import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Message;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.types.ExecutionTrace;
import io.filecoin.protocol.domain.types.MessageReceipt;

import java.io.Serializable;
import java.math.BigInteger;

public class InvocResult implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Cid msgCid;
    private Message msg;
    private MessageReceipt msgRct;
    private MsgGasCost gasCost;
    private ExecutionTrace executionTrace;
    private String error;
    private Long duration;

    public InvocResult() {
    }

    public static class MsgGasCost implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;
        private Cid message;                    // Can be different than requested, in case it was replaced, but only gas values changed
        private BigInteger gasUsed;
        private BigInteger baseFeeBurn;
        private BigInteger overEstimationBurn;
        private BigInteger minerPenalty;
        private BigInteger minerTip;
        private BigInteger refund;
        private BigInteger totalCost;

        public MsgGasCost() {
        }

        public Cid getMessage() {
            return message;
        }

        public void setMessage(Cid message) {
            this.message = message;
        }

        public BigInteger getGasUsed() {
            return gasUsed;
        }

        public void setGasUsed(BigInteger gasUsed) {
            this.gasUsed = gasUsed;
        }

        public BigInteger getBaseFeeBurn() {
            return baseFeeBurn;
        }

        public void setBaseFeeBurn(BigInteger baseFeeBurn) {
            this.baseFeeBurn = baseFeeBurn;
        }

        public BigInteger getOverEstimationBurn() {
            return overEstimationBurn;
        }

        public void setOverEstimationBurn(BigInteger overEstimationBurn) {
            this.overEstimationBurn = overEstimationBurn;
        }

        public BigInteger getMinerPenalty() {
            return minerPenalty;
        }

        public void setMinerPenalty(BigInteger minerPenalty) {
            this.minerPenalty = minerPenalty;
        }

        public BigInteger getMinerTip() {
            return minerTip;
        }

        public void setMinerTip(BigInteger minerTip) {
            this.minerTip = minerTip;
        }

        public BigInteger getRefund() {
            return refund;
        }

        public void setRefund(BigInteger refund) {
            this.refund = refund;
        }

        public BigInteger getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(BigInteger totalCost) {
            this.totalCost = totalCost;
        }
    }


    public Cid getMsgCid() {
        return msgCid;
    }

    public void setMsgCid(Cid msgCid) {
        this.msgCid = msgCid;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public MessageReceipt getMsgRct() {
        return msgRct;
    }

    public void setMsgRct(MessageReceipt msgRct) {
        this.msgRct = msgRct;
    }

    public MsgGasCost getGasCost() {
        return gasCost;
    }

    public void setGasCost(MsgGasCost gasCost) {
        this.gasCost = gasCost;
    }

    public ExecutionTrace getExecutionTrace() {
        return executionTrace;
    }

    public void setExecutionTrace(ExecutionTrace executionTrace) {
        this.executionTrace = executionTrace;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
