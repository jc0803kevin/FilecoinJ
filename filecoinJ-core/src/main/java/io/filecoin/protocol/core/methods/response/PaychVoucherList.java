package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.paych.SignedVoucher;

import java.util.List;

public class PaychVoucherList extends Response<List<SignedVoucher>> {

    @Override
    public List<SignedVoucher> getResult() {
        return super.getResult();
    }
}
