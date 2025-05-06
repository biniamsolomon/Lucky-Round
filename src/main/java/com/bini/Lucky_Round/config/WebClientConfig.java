package com.bini.Lucky_Round.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    private static final Iterable<String> allowedCiphers = List.of("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
            "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");


    private HttpClient getHttpClientWithoutSSLChecking() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        return HttpClient.create().secure(t -> t.sslContext(sslContext));
    }


    private HttpClient getHttpClientWithoutSSLCheckingg2() throws SSLException {
        SslContext sslContext = getSslContext();

        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(15000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS));
                })
                .responseTimeout(Duration.ofSeconds(6))

                .secure(t -> t.sslContext(sslContext)).responseTimeout(Duration.ofSeconds(6));
    }

    private static SslContext getSslContext() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        return sslContext;
    }

    @Bean
    public WebClient webClient() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .protocols("SSLv3","TLSv1","TLSv1.1","TLSv1.2")
                .ciphers(allowedCiphers)
                .build();

        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

//    @Bean
//    public WebClient bigDataClient(WebClient.Builder builder) {
//        return builder
//                .baseUrl("https://mpesa-statement-api.bigdata.blueprint.lab")
//                .build();
//    }


}
