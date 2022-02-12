package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.builtin.miner.SectorOnChainInfo;

import java.util.List;

public class StateMinerActiveSectors extends Response<List<SectorOnChainInfo>> {

    @Override
    public List<SectorOnChainInfo> getResult() {
        return super.getResult();
    }
}
