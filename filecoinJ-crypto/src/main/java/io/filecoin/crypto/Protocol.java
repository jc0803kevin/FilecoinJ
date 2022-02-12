package io.filecoin.crypto;

public enum Protocol {
    ID("0"),
    SECP256K1("1"),   //普通地址
    ACTOR("2"),       //合约地址
    BLS("3");         //矿工地址

    ;

    private final String code;

    Protocol(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Protocol getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (Protocol value : Protocol.values()) {
            if (value.code.equalsIgnoreCase(code)) {
                return value;
            }
        }
        return null;
    }
}
