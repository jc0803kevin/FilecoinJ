package io.filecoin.tx.multisig.types;

import io.filecoin.crypto.UnmarshalCBOR;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.crypto.cbor.Cborable;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;

public class ApproveReturn implements Serializable, UnmarshalCBOR<ApproveReturn> {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private boolean applied;
    private Long exitCode;
    private String ret;

    @Override
    public ApproveReturn unmarshalCBOR(byte[] payload) {
        CborObject.CborList cborList = (CborObject.CborList) CborObject.fromByteArray(payload);
        List<? extends Cborable> value = cborList.value;

        CborObject.CborBoolean applied = (CborObject.CborBoolean) value.get(0);
        CborObject.CborLong exitCode = (CborObject.CborLong) value.get(1);
        CborObject.CborByteArray ret = (CborObject.CborByteArray) value.get(2);

        ApproveReturn approveReturn = new ApproveReturn();
        approveReturn.setApplied(applied.value);
        approveReturn.setExitCode(exitCode.value);
        approveReturn.setRet(new String(ret.value));
        return approveReturn;
    }

    public ApproveReturn() {
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public Long getExitCode() {
        return exitCode;
    }

    public void setExitCode(Long exitCode) {
        this.exitCode = exitCode;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }
}
