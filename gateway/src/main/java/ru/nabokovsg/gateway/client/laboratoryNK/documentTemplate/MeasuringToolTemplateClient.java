package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;

@Service
public class MeasuringToolTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/measuring/tool";
    private static final String DELIMITER = "/";

    @Autowired
    public MeasuringToolTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}
