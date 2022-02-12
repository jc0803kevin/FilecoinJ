package io.filecoin.tx.multisig.types;

import io.filecoin.crypto.UnmarshalCBOR;
import io.filecoin.crypto.cbor.CborObject;
import io.filecoin.crypto.cbor.Cborable;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.util.List;

public class ProposeReturn implements Serializable, UnmarshalCBOR<ProposeReturn> {
    private static final long serialVersionUID = Constants.serialVersionUID;

    private Long txnID;
    private boolean applied;
    private Long exitCode;
    private String ret;


    @Override
    public ProposeReturn unmarshalCBOR(byte[] payload) {

        CborObject.CborList cborList = (CborObject.CborList) CborObject.fromByteArray(payload);
        List<? extends Cborable> value = cborList.value;

        CborObject.CborLong txnID = (CborObject.CborLong) value.get(0);
        CborObject.CborBoolean applied = (CborObject.CborBoolean) value.get(1);
        CborObject.CborLong exitCode = (CborObject.CborLong) value.get(2);
        CborObject.CborByteArray ret = (CborObject.CborByteArray) value.get(3);

        ProposeReturn proposeReturn = new ProposeReturn();
        proposeReturn.setTxnID(txnID.value);
        proposeReturn.setApplied(applied.value);
        proposeReturn.setExitCode(exitCode.value);
        proposeReturn.setRet(new String(ret.value));

        return proposeReturn;
    }

    public ProposeReturn() {
    }

    public Long getTxnID() {
        return txnID;
    }

    public void setTxnID(Long txnID) {
        this.txnID = txnID;
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
