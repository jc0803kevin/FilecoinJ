package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.core.Response;

import java.util.List;

public class MpoolBatchPushUntrusted extends Response<List<Cid>> {

    @Override
    public List<Cid> getResult() {
        return super.getResult();
    }
}
