package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table.NewTableTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.table.UpdateTableTemplateDto;

@Service
public class TableTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/table";
    private static final String DELIMITER = "/";

    @Autowired
    public TableTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewTableTemplateDto tableDto) {
        return post(API_PREFIX, tableDto);
    }

    public Mono<Object> update(UpdateTableTemplateDto tableDto) {
        return patch(API_PREFIX, tableDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}