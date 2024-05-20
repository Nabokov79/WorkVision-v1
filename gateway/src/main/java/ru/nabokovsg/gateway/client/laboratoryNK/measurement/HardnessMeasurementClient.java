package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.hardnessMeasurement.NewHardnessMeasurementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.hardnessMeasurement.UpdateHardnessMeasurementDto;

@Service
public class HardnessMeasurementClient extends BaseClient {

    private static final String API_PREFIX = "/measurement/hardness";
    private static final String DELIMITER = "/";

    @Autowired
    public HardnessMeasurementClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Flux<Object> save(NewHardnessMeasurementDto measurementDto) {
        return postAll(API_PREFIX, measurementDto);
    }

    public Flux<Object> update(UpdateHardnessMeasurementDto measurementDto) {
        return patchAll(API_PREFIX, measurementDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}