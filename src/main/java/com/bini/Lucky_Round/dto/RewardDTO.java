package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("rewardName")
    private String rewardName;

    @JsonProperty("rewardValue")
    private String rewardValue;

    @JsonProperty("rewardDescription")
    private String rewardDescription;

    @JsonProperty("rewardType")
    private RewardTypeDTO rewardType;   // Nested DTO

    @JsonProperty("transactionType")
    private TransactionTypeDTO transactionType;  // Nullable
}
