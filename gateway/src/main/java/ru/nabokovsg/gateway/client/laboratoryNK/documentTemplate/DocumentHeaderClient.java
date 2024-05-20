package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.NewDocumentHeaderForProtocolDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.NewDocumentHeaderForReportDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.UpdateDocumentHeaderForProtocolDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.documentHeader.UpdateDocumentHeaderForReportDto;

@Service
public class DocumentHeaderClient extends BaseClient {

    private static final String API_PREFIX = "/template/document/header";
    private static final String DELIMITER = "/";

    @Autowired
    public DocumentHeaderClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveForReport(NewDocumentHeaderForReportDto headerDto) {
        return post(API_PREFIX, headerDto);
    }

    public Mono<Object> updateForReport(UpdateDocumentHeaderForReportDto headerDto) {
        return patch(API_PREFIX, headerDto);
    }

    public Mono<Object> saveForProtocol(NewDocumentHeaderForProtocolDto headerDto) {
        return post(API_PREFIX, headerDto);
    }

    public Mono<Object> updateForProtocol(UpdateDocumentHeaderForProtocolDto headerDto) {
        return patch(API_PREFIX, headerDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}
