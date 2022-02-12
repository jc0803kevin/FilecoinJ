package io.filecoin.protocol.core.methods.response;

import io.filecoin.crypto.types.Cid;
import io.filecoin.protocol.constants.Constants;
import io.filecoin.protocol.core.Response;
import io.filecoin.protocol.domain.InvocResult;

import java.io.Serializable;
import java.util.List;

public class StateCompute extends Response<StateCompute.ComputeStateOutput> {

    @Override
    public ComputeStateOutput getResult() {
        return super.getResult();
    }

    public static class ComputeStateOutput implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        private Cid root;
        private List<InvocResult> trace;

        public ComputeStateOutput() {
        }

        public Cid getRoot() {
            return root;
        }

        public void setRoot(Cid root) {
            this.root = root;
        }

        public List<InvocResult> getTrace() {
            return trace;
        }

        public void setTrace(List<InvocResult> trace) {
            this.trace = trace;
        }
    }

}
