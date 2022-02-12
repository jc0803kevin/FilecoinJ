package io.filecoin.crypto;

import org.apache.commons.lang3.StringUtils;

public enum NetworkParameters {

    MAIN_NET("f"),
    TEST_NET("t"),

    ;

    private final String prefix;

    NetworkParameters(String prefix) {
        this.prefix = prefix;
    }


    public static NetworkParameters getByPrefix(String prefix) {
        if (StringUtils.isBlank(prefix)) {
            return null;
        }

        for (NetworkParameters value : NetworkParameters.values()) {
            if (value.prefix.equalsIgnoreCase(prefix)) {
                return value;
            }
        }
        return null;
    }

    public static boolean isMainNet(NetworkParameters parameters){
        if(parameters != null && parameters == MAIN_NET){
            return true;
        }
        return false;
    }

    public String getPrefix() {
        return prefix;
    }
}
