package ru.nabokovsg.laboratoryNK.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value(value = "${company-server.url}")
    private String companyUrl;
    @Value(value = "${diagnosedNK-server.url}")
    private String diagnosedNKUrl;

    @Bean
    public WebClient webCompany() {
        return WebClient.builder()
                .baseUrl(companyUrl)
                .build();
    }

    @Bean
    public WebClient webDiagnosedNK() {
        return WebClient.builder()
                .baseUrl(diagnosedNKUrl)
                .build();
    }
}
