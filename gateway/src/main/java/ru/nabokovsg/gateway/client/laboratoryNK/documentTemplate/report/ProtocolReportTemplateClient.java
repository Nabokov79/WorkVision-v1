package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.reportProtocol.NewProtocolReportTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.reportProtocol.UpdateProtocolReportTemplateDto;

@Service
public class ProtocolReportTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/report/protocol";
    private static final String DELIMITER = "/";

    @Autowired
    public ProtocolReportTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewProtocolReportTemplateDto protocolDto) {
        return post(API_PREFIX, protocolDto);
    }

    public Mono<Object> update(UpdateProtocolReportTemplateDto protocolDto) {
        return patch(API_PREFIX, protocolDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "/all/", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}