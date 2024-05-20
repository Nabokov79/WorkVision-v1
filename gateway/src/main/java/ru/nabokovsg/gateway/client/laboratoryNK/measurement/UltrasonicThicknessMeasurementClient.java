package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.utm.NewUltrasonicThicknessMeasurementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.utm.UpdateUltrasonicThicknessMeasurementDto;

@Service
public class UltrasonicThicknessMeasurementClient extends BaseClient {

    private static final String API_PREFIX = "/measurement/ut";
    private static final String DELIMITER = "/";

    @Autowired
    public UltrasonicThicknessMeasurementClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewUltrasonicThicknessMeasurementDto measurementDto) {
        return post(API_PREFIX, measurementDto);
    }

    public Mono<Object> update(UpdateUltrasonicThicknessMeasurementDto measurementDto) {
        return patch(API_PREFIX, measurementDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}