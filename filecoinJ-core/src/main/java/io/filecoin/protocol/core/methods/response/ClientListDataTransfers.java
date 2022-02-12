package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.types.DataTransferChannel;

import java.util.List;

public class ClientListDataTransfers extends Response<List<DataTransferChannel>> {

    @Override
    public List<DataTransferChannel> getResult() {
        return super.getResult();
    }
}
