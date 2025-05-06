package com.bini.Lucky_Round.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Initiator {

    @JsonProperty("IdentifierType")
    private String identifierType;

    @NotNull
    @NotBlank
    @JsonProperty("Identifier")
    private String identifier;

    @JsonProperty("ShortCode")
    private String shortCode;

    @JsonProperty("SecurityCredential")
    private String securityCredential;

    @JsonProperty("SecretKey")
    private String secretKey;
}
