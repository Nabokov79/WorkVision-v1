package ru.nabokovsg.gateway.client.laboratoryNK.equipmentDiagnosed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.partElement.NewPartElementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.partElement.UpdatePartElementDto;

@Service
public class PartElementClient extends BaseClient {

    private static final String API_PREFIX = "/equipments/elements/parts";
    private static final String DELIMITER = "/";

    @Autowired
    public PartElementClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewPartElementDto partElementDto) {
        return post(API_PREFIX, partElementDto);
    }

    public Mono<Object> update(UpdatePartElementDto partElementDto) {
        return patch(API_PREFIX, partElementDto);
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
