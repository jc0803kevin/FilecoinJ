package io.filecoin.protocol.domain.types;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.filecoin.protocol.constants.Constants;

import java.io.Serializable;


public class BeaconEntry implements Serializable {
    private static final long serialVersionUID = Constants.serialVersionUID;

    @JsonProperty("Round")
    private Long round;
    @JsonProperty("Data")
    private String data;

    public BeaconEntry() {
    }

    public Long getRound() {
        return round;
    }

    public void setRound(Long round) {
        this.round = round;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
