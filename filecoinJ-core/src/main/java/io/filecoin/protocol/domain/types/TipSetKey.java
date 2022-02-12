package io.filecoin.protocol.domain.types;


import io.filecoin.crypto.types.Cid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class TipSetKey extends ArrayList<Cid>{

    public static TipSetKey of(Collection<Cid> cids) {
        TipSetKey result = new TipSetKey();
        result.addAll(cids);
        return result;
    }

    public static TipSetKey of(Cid... cids) {
        TipSetKey result = new TipSetKey();
        result.addAll(Arrays.asList(cids));
        return result;
    }

    public static TipSetKey of(String... cids) {
        TipSetKey result = new TipSetKey();
        for (String cid : cids) {
            result.add(Cid.of(cid));
        }
        return result;
    }

    public static TipSetKey empty() {
        TipSetKey result = new TipSetKey();
        return result;
    }
}
