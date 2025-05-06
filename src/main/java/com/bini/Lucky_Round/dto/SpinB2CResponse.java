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
public class SpinB2CResponse {

    @JsonProperty("response_code")
    private String responseCode;

}
