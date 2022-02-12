package io.filecoin.protocol.domain.types;

import io.filecoin.crypto.types.Message;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;

public class ExecutionTrace implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Message msg;
    private MessageReceipt msgRct;
    private String error;
    private Long duration;
    private List<GasTrace> gasCharges;
    private List<ExecutionTrace> subcalls;

    public static class GasTrace implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String name;
        private Loc[] location;             //`json:"loc"`
        private Long totalGas;              // `json:"tg"`
        private Long computeGas;            // `json:"cg"`
        private Long storageGas;            // `json:"sg"`
        private Long totalVirtualGas;       // `json:"vtg"`
        private Long virtualComputeGas;     // `json:"vcg"`
        private Long virtualStorageGas;     // `json:"vsg"`
        private Long timeTaken;             // `json:"tt"`
        private Long extra;                 //   `json:"ex,omitempty"`
//    private Long Callers ;// `json:"-"`


        public GasTrace() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Loc[] getLocation() {
            return location;
        }

        public void setLocation(Loc[] location) {
            this.location = location;
        }

        public Long getTotalGas() {
            return totalGas;
        }

        public void setTotalGas(Long totalGas) {
            this.totalGas = totalGas;
        }

        public Long getComputeGas() {
            return computeGas;
        }

        public void setComputeGas(Long computeGas) {
            this.computeGas = computeGas;
        }

        public Long getStorageGas() {
            return storageGas;
        }

        public void setStorageGas(Long storageGas) {
            this.storageGas = storageGas;
        }

        public Long getTotalVirtualGas() {
            return totalVirtualGas;
        }

        public void setTotalVirtualGas(Long totalVirtualGas) {
            this.totalVirtualGas = totalVirtualGas;
        }

        public Long getVirtualComputeGas() {
            return virtualComputeGas;
        }

        public void setVirtualComputeGas(Long virtualComputeGas) {
            this.virtualComputeGas = virtualComputeGas;
        }

        public Long getVirtualStorageGas() {
            return virtualStorageGas;
        }

        public void setVirtualStorageGas(Long virtualStorageGas) {
            this.virtualStorageGas = virtualStorageGas;
        }

        public Long getTimeTaken() {
            return timeTaken;
        }

        public void setTimeTaken(Long timeTaken) {
            this.timeTaken = timeTaken;
        }

        public Long getExtra() {
            return extra;
        }

        public void setExtra(Long extra) {
            this.extra = extra;
        }
    }

    public static class Loc implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String file;
        private Integer line;
        private String function;

        public Loc() {
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public Integer getLine() {
            return line;
        }

        public void setLine(Integer line) {
            this.line = line;
        }

        public String getFunction() {
            return function;
        }

        public void setFunction(String function) {
            this.function = function;
        }
    }

    public ExecutionTrace() {
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

    public List<GasTrace> getGasCharges() {
        return gasCharges;
    }

    public void setGasCharges(List<GasTrace> gasCharges) {
        this.gasCharges = gasCharges;
    }

    public List<ExecutionTrace> getSubcalls() {
        return subcalls;
    }

    public void setSubcalls(List<ExecutionTrace> subcalls) {
        this.subcalls = subcalls;
    }
}
