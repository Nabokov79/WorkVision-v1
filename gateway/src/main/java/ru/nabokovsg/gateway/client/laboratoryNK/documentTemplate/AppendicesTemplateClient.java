package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.appendices.NewAppendicesTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.appendices.UpdateAppendicesTemplateDto;

@Service
public class AppendicesTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/appendices";
    private static final String DELIMITER = "/";

    @Autowired
    public AppendicesTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewAppendicesTemplateDto appendicesDto) {
        return post(API_PREFIX, appendicesDto);
    }

    public Mono<Object> update(UpdateAppendicesTemplateDto appendicesDto) {
        return patch(API_PREFIX, appendicesDto);
    }

    public Mono<String> delete(Long id){
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}
