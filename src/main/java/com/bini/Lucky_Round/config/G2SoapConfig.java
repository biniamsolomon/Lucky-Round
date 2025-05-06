package com.bini.Lucky_Round.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
@RequiredArgsConstructor
public class G2SoapConfig {
	private final ConfigProperties configProperties;

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.huawei.cps.synccpsinterface");

		WebServiceTemplate template = new WebServiceTemplate(marshaller);
		template.setDefaultUri(this.configProperties.getG2SyncUrl());

		template.afterPropertiesSet();
		return template;
	}

}
