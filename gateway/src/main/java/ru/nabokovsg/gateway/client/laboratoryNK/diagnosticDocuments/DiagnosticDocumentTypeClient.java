package ru.nabokovsg.gateway.client.laboratoryNK.diagnosticDocuments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.diagnosticDocumentsType.NewDiagnosticDocumentTypeDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.diagnosticDocument.diagnosticDocumentsType.UpdateDiagnosticDocumentTypeDto;

@Service
public class DiagnosticDocumentTypeClient extends BaseClient {

    private static final String API_PREFIX = "/document/type";
    private static final String DELIMITER = "/";

    @Autowired
    public DiagnosticDocumentTypeClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewDiagnosticDocumentTypeDto documentTypeDto) {
        return post(API_PREFIX, documentTypeDto);
    }

    public Mono<Object> update(UpdateDiagnosticDocumentTypeDto documentTypeDto) {
        return patch(API_PREFIX, documentTypeDto);
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}