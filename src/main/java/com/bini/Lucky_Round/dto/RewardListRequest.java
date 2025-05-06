package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RewardListRequest {
    @JsonProperty("ReqRefID")
    public String reqRefID;
    @JsonProperty("SourceSystem")
    public String sourceSystem;
    @JsonProperty("RewardDTO")
    public RewardDTO reward;
    @JsonProperty("Timestamp")
    public String timeStamp;
}
