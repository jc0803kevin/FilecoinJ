package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateGetActor extends Response<StateGetActor.Actor> {

    @Override
    public Actor getResult() {
        return super.getResult();
    }

    public static class Actor implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid code;
        private Cid head;
        private Long nonce;
        private BigInteger balance;

        public Actor() {
        }

        public Cid getCode() {
            return code;
        }

        public void setCode(Cid code) {
            this.code = code;
        }

        public Cid getHead() {
            return head;
        }

        public void setHead(Cid head) {
            this.head = head;
        }

        public Long getNonce() {
            return nonce;
        }

        public void setNonce(Long nonce) {
            this.nonce = nonce;
        }

        public BigInteger getBalance() {
            return balance;
        }

        public void setBalance(BigInteger balance) {
            this.balance = balance;
        }
    }
}
