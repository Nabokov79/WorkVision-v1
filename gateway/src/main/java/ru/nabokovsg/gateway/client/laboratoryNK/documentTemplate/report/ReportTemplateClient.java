package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;

@Service
public class ReportTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/report";
    private static final String DELIMITER = "/";

    @Autowired
    public ReportTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX, "/all"));
    }
}