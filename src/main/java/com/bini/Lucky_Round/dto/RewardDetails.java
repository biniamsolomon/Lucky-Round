package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class RewardDetails {

    @JsonProperty("RewardType")
    private String rewardType;
    @JsonProperty("RewardAmount")
    private String rewardAmount;

}
