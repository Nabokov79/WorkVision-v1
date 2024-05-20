package ru.nabokovsg.gateway.client.laboratoryNK.equipmentDiagnosed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.element.NewElementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.element.UpdateElementDto;

@Service
public class EquipmentElementClient extends BaseClient {

    private static final String API_PREFIX = "/equipments/elements";
    private static final String DELIMITER = "/";

    @Autowired
    public EquipmentElementClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewElementDto elementDto) {
        return post(API_PREFIX, elementDto);
    }

    public Mono<Object> update(UpdateElementDto elementDto) {
        return patch(API_PREFIX, elementDto);
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