package ru.nabokovsg.gateway.client.laboratoryNK.measurement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.employeeRecommendation.NewEmployeeRecommendationDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.measurement.common.employeeRecommendation.UpdateEmployeeRecommendationDto;

@Service
public class EmployeeRecommendationClient extends BaseClient {

    private static final String API_PREFIX = "/recommendation/employee";
    private static final String DELIMITER = "/";

    @Autowired
    public EmployeeRecommendationClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewEmployeeRecommendationDto recommendationDto) {
        return post(API_PREFIX, recommendationDto);
    }

    public Mono<Object> update(UpdateEmployeeRecommendationDto recommendationDto) {
        return patch(API_PREFIX, recommendationDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}
