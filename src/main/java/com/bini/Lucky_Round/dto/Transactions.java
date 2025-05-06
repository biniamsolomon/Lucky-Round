package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transactions {

    @JsonProperty("TransactionID")
    private String transactionId;

    @JsonProperty("TransactionType")
    private String transactionType;

    @JsonProperty("Amount")
    private String amount;

    @JsonProperty("TransactionDate")
    private String transactionDate;

    @JsonProperty("ReasonTypeId")
    private String reasonTypeId;
}
