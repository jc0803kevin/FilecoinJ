package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;

import java.io.Serializable;
import java.math.BigInteger;

public class StateReadState extends Response<StateReadState.ActorState> {

    @Override
    public ActorState getResult() {
        return super.getResult();
    }

    public static class ActorState implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private BigInteger balance;
        private Cid code;
        private String state;

        public ActorState() {
        }

        public BigInteger getBalance() {
            return balance;
        }

        public void setBalance(BigInteger balance) {
            this.balance = balance;
        }

        public Cid getCode() {
            return code;
        }

        public void setCode(Cid code) {
            this.code = code;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
