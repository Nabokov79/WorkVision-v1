package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocol.NewProtocolTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.protocol.UpdateProtocolTemplateDto;

@Service
public class ProtocolTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/protocol/survey";
    private static final String DELIMITER = "/";

    @Autowired
    public ProtocolTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewProtocolTemplateDto protocolDto) {
        return post(API_PREFIX, protocolDto);
    }

    public Mono<Object> update(UpdateProtocolTemplateDto protocolDto) {
        return patch(API_PREFIX, protocolDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}