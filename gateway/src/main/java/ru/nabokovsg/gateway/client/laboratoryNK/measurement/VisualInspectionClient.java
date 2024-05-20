package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.visualInspection.NewVisualInspectionDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.vms.visualInspection.UpdateVisualInspectionDto;

@Service
public class VisualInspectionClient extends BaseClient {

    private static final String API_PREFIX = "/measurement/visual/inspection";
    private static final String DELIMITER = "/";

    @Autowired
    public VisualInspectionClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewVisualInspectionDto inspectionDto) {
        return post(API_PREFIX, inspectionDto);
    }

    public Mono<Object> update(UpdateVisualInspectionDto inspectionDto) {
        return patch(API_PREFIX, inspectionDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}