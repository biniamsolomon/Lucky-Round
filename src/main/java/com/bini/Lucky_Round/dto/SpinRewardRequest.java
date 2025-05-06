package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SpinRewardRequest {

    @NotNull
    @NotBlank
    @JsonProperty("RequestRefID")
    @Size(min = 4, max=64)
    private String requestRefID;

    @JsonProperty("SourceSystem")
    private String sourceSystem;

    @JsonProperty("Remark")
    private String remark;

    @JsonProperty("Timestamp")
    private String timestamp;

    @NotNull
    @NotBlank
    @JsonProperty("Transactions")
    private Transactions transactions;

    @NotNull
    @NotBlank
    @JsonProperty("Initiator")
    private Initiator initiators;
}
