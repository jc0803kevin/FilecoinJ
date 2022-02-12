package io.filecoin.protocol;

import io.filecoin.protocol.core.FileCoin;
import io.filecoin.protocol.core.JsonRpc2_0FileCoinJ;

public interface FileCoinJ extends FileCoin {

    static FileCoinJ build(FilecoinjService filecoinjService) {
        return new JsonRpc2_0FileCoinJ(filecoinjService);
    }

    void shutdown();
}
