package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.paych.SignedVoucher;

import java.io.Serializable;
import java.util.List;

public class PaychNewPayment extends Response<PaychNewPayment.PaymentInfo> {

    @Override
    public PaymentInfo getResult() {
        return super.getResult();
    }

    public class PaymentInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String channel;
        private Cid waitSentinel;
        private List<SignedVoucher> vouchers;

        public PaymentInfo() {
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public Cid getWaitSentinel() {
            return waitSentinel;
        }

        public void setWaitSentinel(Cid waitSentinel) {
            this.waitSentinel = waitSentinel;
        }

        public List<SignedVoucher> getVouchers() {
            return vouchers;
        }

        public void setVouchers(List<SignedVoucher> vouchers) {
            this.vouchers = vouchers;
        }
    }
}
