package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement.NewGeodeticMeasurementEquipmentDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.geodesicMeasurement.UpdateGeodesyMeasurementDto;

import java.util.List;

@Service
public class GeodesicMeasurementClient extends BaseClient {

    private static final String API_PREFIX = "/measurement/geodesic";
    private static final String DELIMITER = "/";

    @Autowired
    public GeodesicMeasurementClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Flux<Object> save(NewGeodeticMeasurementEquipmentDto measurementsDto) {
        return postAll(API_PREFIX, measurementsDto);
    }

    public Flux<Object> update(List<UpdateGeodesyMeasurementDto> measurementsDto) {
        return patchAll(API_PREFIX, measurementsDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}