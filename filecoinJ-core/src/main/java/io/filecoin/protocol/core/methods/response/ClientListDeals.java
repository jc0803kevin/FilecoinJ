package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.DealInfo;

import java.util.List;

public class ClientListDeals extends Response<List<DealInfo>> {

    @Override
    public List<DealInfo> getResult() {
        return super.getResult();
    }
}
