package io.filecoin.protocol.domain.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.filecoin.crypto.types.Cid;
import io.filecoin.crypto.types.Signature;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class BlockHeader implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    @JsonProperty("Miner")
    private String miner;                       // 0 unique per block/miner
    @JsonProperty("Ticket")
    private Ticket ticket;                      // 1 unique per block/miner: should be a valid VRF
    @JsonProperty("ElectionProof")
    private ElectionProof electionProof;        // 2 unique per block/miner: should be a valid VRF
    @JsonProperty("BeaconEntries")
    private List<BeaconEntry> beaconEntries;    // 3 identical for all blocks in same tipset
    @JsonProperty("WinPoStProof")
    private List<PoStProof> winPoStProof;       // 4 unique per block/miner
    @JsonProperty("Parents")
    private List<Cid> parents;                  // 5 identical for all blocks in same tipset
    @JsonProperty("ParentWeight")
    private BigInteger parentWeight;            // 6 identical for all blocks in same tipset
    @JsonProperty("Height")
    private Long height;                        // 7 identical for all blocks in same tipset
    @JsonProperty("ParentStateRoot")
    private Cid parentStateRoot;                // 8 identical for all blocks in same tipset
    @JsonProperty("ParentMessageReceipts")
    private Cid parentMessageReceipts;          // 9 identical for all blocks in same tipset
    @JsonProperty("Messages")
    private Cid messages;                       // 10 unique per block
    @JsonProperty("BLSAggregate")
    private Signature bLSAggregate;             // 11 unique per block: aggrregate of BLS messages from above
    @JsonProperty("Timestamp")
    private Long timestamp;                     // 12 identical for all blocks in same tipset / hard-tied to the value of Height above
    @JsonProperty("BlockSig")
    private Signature blockSig;                 // 13 unique per block/miner: miner signature
    @JsonProperty("ForkSignaling")
    private Long forkSignaling;                 // 14 currently unused/undefined
    @JsonProperty("ParentBaseFee")
    private BigInteger parentBaseFee;           // 15 identical for all blocks in same tipset: the base fee after executing parent tipset
    //@JsonProperty("")
    //private Boolean validated;                  // 16 internal, true if the signature has been validated

    public static class Ticket implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        @JsonProperty("VRFProof")
        private String vRFProof;

        public Ticket() {
        }

        public String getvRFProof() {
            return vRFProof;
        }

        public void setvRFProof(String vRFProof) {
            this.vRFProof = vRFProof;
        }
    }

    public static class PoStProof implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        @JsonProperty("PoStProof")
        private Integer poStProof;
        @JsonProperty("ProofBytes")
        private String proofBytes;

        public PoStProof() {
        }

        public Integer getPoStProof() {
            return poStProof;
        }

        public void setPoStProof(Integer poStProof) {
            this.poStProof = poStProof;
        }

        public String getProofBytes() {
            return proofBytes;
        }

        public void setProofBytes(String proofBytes) {
            this.proofBytes = proofBytes;
        }
    }

    public static class ElectionProof implements Serializable {
        private static final long serialVersionUID = Constants.serialVersionUID;

        @JsonProperty("WinCount")
        private Long winCount;
        @JsonProperty("VRFProof")
        private String vRFProof;

        public ElectionProof() {
        }

        public Long getWinCount() {
            return winCount;
        }

        public void setWinCount(Long winCount) {
            this.winCount = winCount;
        }

        public String getvRFProof() {
            return vRFProof;
        }

        public void setvRFProof(String vRFProof) {
            this.vRFProof = vRFProof;
        }
    }
}
