package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;

public class ID extends Response<String> {

    public String getID() {
        return getResult();
    }

}
