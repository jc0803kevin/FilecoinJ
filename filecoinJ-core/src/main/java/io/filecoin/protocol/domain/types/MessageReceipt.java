package io.filecoin.protocol.domain.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.domain.exitcode.ExitCode;

import java.io.Serializable;
import java.math.BigInteger;


public class MessageReceipt implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private ExitCode exitCode;

    @JsonProperty("Return")
    private String ret;

    private BigInteger gasUsed;

    public MessageReceipt() {
    }

    public ExitCode getExitCode() {
        return exitCode;
    }

    public void setExitCode(ExitCode exitCode) {
        this.exitCode = exitCode;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }
}
