package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.QueryOffer;

import java.util.List;

public class ClientFindData extends Response<List<QueryOffer>> {
    @Override
    public List<QueryOffer> getResult() {
        return super.getResult();
    }
}
