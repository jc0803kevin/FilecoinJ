package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.types.MessageReceipt;

import java.util.List;

public class ChainGetParentReceipts extends Response<List<MessageReceipt>> {

    @Override
    public List<MessageReceipt> getResult() {
        return super.getResult();
    }
}
