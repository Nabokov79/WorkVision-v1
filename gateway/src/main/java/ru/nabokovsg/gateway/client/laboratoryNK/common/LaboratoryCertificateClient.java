package ru.nabokovsg.gateway.client.laboratoryNK.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.laboratoryCertificate.NewLaboratoryCertificateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.common.laboratoryCertificate.UpdateLaboratoryCertificateDto;

@Service
public class LaboratoryCertificateClient extends BaseClient {

    private static final String API_PREFIX = "/laboratory/certificate";
    private static final String DELIMITER = "/";

    @Autowired
    public LaboratoryCertificateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewLaboratoryCertificateDto certificateDto) {
        return post(API_PREFIX, certificateDto);
    }

    public Mono<Object> update(UpdateLaboratoryCertificateDto certificateDto) {
        return patch(API_PREFIX, certificateDto);
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}