package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTypeDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("typeName")
    private String typeName;
}
