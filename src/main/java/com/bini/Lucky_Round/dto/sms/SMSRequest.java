package com.bini.Lucky_Round.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SMSRequest {

	@JsonProperty("RequestRefID")
	public String requestRefID;

	@JsonProperty("CommandID")
	public String commandID;

	@JsonProperty("Sender")
	public Sender sender;

	@JsonProperty("Receiver")
	public List<Receiver> receivers;

	@JsonProperty("Content")
	public String content;

	@JsonProperty("MessageType")
	public String messageType;

}
