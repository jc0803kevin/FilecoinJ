package io.filecoin.crypto.multi;

public enum MethodsMultisig {

    Constructor(1, ""),
    Propose(2, "Propose"),
    Approve(3, "Approve"),
    Cancel(4, "Cancel"),
    AddSigner(5, "AddSigner"),
    RemoveSigner(6, "RemoveSigner"),
    SwapSigner(7, "SwapSigner"),
    ChangeNumApprovalsThreshhold(8, "ChangeNumApprovalsThreshhold"),
    LockBalance(9, "LockBalance");

    private long method;
    private String desc;

    MethodsMultisig(long method, String desc) {
        this.method = method;
        this.desc = desc;
    }

    public long getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }
}
