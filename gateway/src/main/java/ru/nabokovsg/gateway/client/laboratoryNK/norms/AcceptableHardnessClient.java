package ru.nabokovsg.gateway.client.laboratoryNK.norms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableHardness.NewAcceptableHardnessDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableHardness.UpdateAcceptableHardnessDto;

@Service
public class AcceptableHardnessClient extends BaseClient {

    private static final String API_PREFIX = "/norms/hardness";
    private static final String DELIMITER = "/";

    @Autowired
    public AcceptableHardnessClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewAcceptableHardnessDto hardnessDto) {
        return post(API_PREFIX, hardnessDto);
    }

    public Mono<Object> update(UpdateAcceptableHardnessDto hardnessDto) {
        return patch(API_PREFIX,hardnessDto);
    }

    public Flux<Object> getAll(Long id) {
        return getAll(String.join(DELIMITER, API_PREFIX, "all", String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}