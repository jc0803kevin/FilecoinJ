package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;

public class PaychGet extends Response<PaychGet.ChannelInfo> {

    @Override
    public ChannelInfo getResult() {
        return super.getResult();
    }

    public static class ChannelInfo implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private String channel;
        private Cid waitSentinel;

        public ChannelInfo() {
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
    }
}
