package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.SignedMessage;
import io.filecoin.protocol.core.Response;

import java.util.List;

public class MpoolBatchPushMessage extends Response<List<SignedMessage>> {

    @Override
    public List<SignedMessage> getResult() {
        return super.getResult();
    }
}
