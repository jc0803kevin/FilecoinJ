package io.filecoin.protocol.core.methods.response;

import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.paych.SignedVoucher;

import java.io.Serializable;
import java.math.BigInteger;

public class PaychVoucherCreate extends Response<PaychVoucherCreate.VoucherCreateResult> {

    @Override
    public VoucherCreateResult getResult() {
        return super.getResult();
    }

    public static class VoucherCreateResult implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        // Voucher that was created, or nil if there was an error or if there
        // were insufficient funds in the channel
        private SignedVoucher voucher;
        // Shortfall is the additional amount that would be needed in the channel
        // in order to be able to create the voucher
        private BigInteger shortfall;

        public VoucherCreateResult() {
        }

        public SignedVoucher getVoucher() {
            return voucher;
        }

        public void setVoucher(SignedVoucher voucher) {
            this.voucher = voucher;
        }

        public BigInteger getShortfall() {
            return shortfall;
        }

        public void setShortfall(BigInteger shortfall) {
            this.shortfall = shortfall;
        }
    }
}
