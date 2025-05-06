package com.bini.Lucky_Round.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SMSResponse {

	@JsonProperty("RequestRefID")
	private String requestRefId;

	@JsonProperty("ResponseCode")
	private String responseCode;

	@JsonProperty("ResponseDescription")
	private String responseDescription;

	//@JsonProperty("ResponseParameters")
	//private List<ReferenceDatum> responseParameters;

}
