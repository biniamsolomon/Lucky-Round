package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpinRewardResponse {

    @JsonProperty("RequestRefID")
    public String requestRefID;

    @JsonProperty("ResponseCode")
    public String responseCode;

    @JsonProperty("ResponseDesc")
    public String responseDesc;

    @JsonProperty("ChannelSessionId")
    public String channelSessionId;

    @JsonProperty("RewardDetails")
    public List<RewardDetails> rewardDetails;
}