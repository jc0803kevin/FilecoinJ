package io.filecoin.crypto;

public enum SignType {
    SigTypeSecp256k1(1, "secp256k1"),
    SigTypeBLS(2, "bls"),

    ;


    private final int code;
    private final String type;

    SignType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
