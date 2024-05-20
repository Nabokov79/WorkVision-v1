package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.equipmentRepair.NewEquipmentRepairDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.equipmentRepair.UpdateEquipmentRepairDto;

@Service
public class EquipmentRepairClient extends BaseClient {

    private static final String API_PREFIX = "/equipment/repair";
    private static final String DELIMITER = "/";

    @Autowired
    public EquipmentRepairClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewEquipmentRepairDto repairDto) {
        return post(API_PREFIX, repairDto);
    }

    public Mono<Object> update(UpdateEquipmentRepairDto repairDto) {
        return patch(API_PREFIX, repairDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}
