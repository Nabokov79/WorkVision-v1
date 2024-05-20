package ru.nabokovsg.gateway.client.laboratoryNK.equipmentDiagnosed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.equipmentType.NewEquipmentTypeDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.equipmentType.UpdateEquipmentTypeDto;

@Service
public class EquipmentTypeClient extends BaseClient {

    private static final String API_PREFIX = "/equipments/type";
    private static final String DELIMITER = "/";

    @Autowired
    public EquipmentTypeClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewEquipmentTypeDto equipmentTypeDto) {
        return post(API_PREFIX,equipmentTypeDto);
    }

    public Mono<Object> update(UpdateEquipmentTypeDto equipmentTypeDto) {
        return patch(API_PREFIX, equipmentTypeDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Flux<Object> getAll() {
        return getAll(String.join(DELIMITER, API_PREFIX, "all"));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}