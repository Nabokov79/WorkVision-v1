package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.conclusion.NewConclusionTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.conclusion.UpdateConclusionTemplateDto;

@Service
public class ConclusionTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/conclusion";
    private static final String DELIMITER = "/";

    @Autowired
    public ConclusionTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewConclusionTemplateDto conclusionDto) {
        return post(API_PREFIX, conclusionDto);
    }

    public Mono<Object> update(UpdateConclusionTemplateDto conclusionDto) {
        return patch(API_PREFIX, conclusionDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}