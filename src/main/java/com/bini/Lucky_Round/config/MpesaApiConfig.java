package com.bini.Lucky_Round.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class MpesaApiConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        String[] packagesToScan = {
                "com.mpesa.ekub.g2.async_g2_schemas"
        };

        marshaller.setPackagesToScan(packagesToScan);

        return marshaller;
    }

    @Bean
    public Jaxb2Marshaller marshallerSync() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        String[] packagesToScan = {
                "com.mpesa.ekub.sync_g2_schemas"
        };

        marshaller.setPackagesToScan(packagesToScan);

        return marshaller;
    }
}
