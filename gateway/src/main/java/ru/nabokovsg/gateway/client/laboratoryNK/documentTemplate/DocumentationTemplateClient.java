package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentationTemplate.NewDocumentationTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentationTemplate.UpdateDocumentationTemplateDto;

import java.util.List;

@Service
public class DocumentationTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/documentation";
    private static final String DELIMITER = "/";

    @Autowired
    public DocumentationTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Flux<Object> save(List<NewDocumentationTemplateDto> templatesDto) {
        return postAll(String.join(DELIMITER, API_PREFIX), templatesDto);
    }

    public Mono<Object> update(UpdateDocumentationTemplateDto templateDto) {
        return patch(String.join(DELIMITER, API_PREFIX), templateDto);
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}