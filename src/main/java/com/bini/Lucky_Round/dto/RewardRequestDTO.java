package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardRequestDTO {
    @JsonProperty("rewardName")
    private String rewardName;

    @JsonProperty("rewardValue")
    private String rewardValue;

    @JsonProperty("rewardDescription")
    private String rewardDescription;

    @JsonProperty("rewardTypeId")
    private Long rewardTypeId;  // ID reference instead of an object

    @JsonProperty("transactionTypeId")
    private Long transactionTypeId;  // Nullable ID reference
}
