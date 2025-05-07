package com.bini.Lucky_Round.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class ConfigProperties {

	private String smsApiUrl;
	private String Timestamp;
	private String smsApiUsername;

	private String smsApiPassword;

	private String SyncUrl;

	private String ResultUrl;

	private String CallerType;

	private String CallerThirdPartyId;

	private String CallerPassword;

	private String InitiatorSecurityCredential;

	private String InitiatorIdentifier;

	private String InitiatorIdentifierType;

	private String b2cInitiatorIdentifier;

	private String b2cInitiatorIdentifierType;

	private String b2cInitiatorSecurityCredential;

	private String b2cInitiatorIdentifierShortCode;

	private String bssBaseUrl;

	private String bssTokenUri;

	private String bssUsername;

	private String bssPassword;

	private String bssBasicDetailsUri;

	private String bssBasicDetailsAction;

	private String bssBasicDetailsRequestId;

	private String bssBasicDetailsChannelId;

	private String bssBasicDetailsStatus;

	private String bssQueryServiceUri;

	private String bssQueryServiceTraceId;

	private String bssQueryServiceChannel;

	private String bssQueryServiceUsername;

	private String bssQueryServiceStatus;

	private String bssQueryServicePlanType;

	private String unlimitedProductId;

	private String redisMaster;

	private String redisSentinels;

	private String redisPassword;

	private Integer inviteExpiresIn;
}
