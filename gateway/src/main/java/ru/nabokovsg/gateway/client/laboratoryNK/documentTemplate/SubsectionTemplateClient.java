package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.subsection.*;

@Service
public class SubsectionTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/subsection";
    private static final String DELIMITER = "/";

    @Autowired
    public SubsectionTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> saveWithUserText(NewSubsectionWithUseTextTemplateDto subsectionsDto) {
        return post(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> updateWithUserText(UpdateSubsectionWithUseTextTemplateDto subsectionsDto) {
        return patch(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> saveWithDivisionData(NewSubsectionWithDivisionDataTemplateDto subsectionsDto) {
        return post(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> updateWithDivisionData(UpdateSubsectionWithDivisionDataTemplateDto subsectionsDto) {
        return patch(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> saveWithDocumentation(NewSubsectionWithDocumentationTemplateDto subsectionsDto) {
        return post(API_PREFIX, subsectionsDto);
    }

    public Mono<Object>updateWithDocumentation(UpdateSubsectionWithDocumentationTemplateDto subsectionsDto) {
        return patch(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> saveWithTable(NewSubsectionWithTableTemplateDto subsectionsDto) {
        return post(API_PREFIX, subsectionsDto);
    }

    public Mono<Object>updateWithTable(UpdateSubsectionWithTableTemplateDto subsectionsDto) {
        return patch(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> saveWithMeasuringTool(NewSubsectionWitMeasuringToolTemplateDto subsectionsDto) {
        return post(API_PREFIX, subsectionsDto);
    }

    public Mono<Object>updateWithMeasuringTool(UpdateSubsectionWitMeasuringToolTemplateDto subsectionsDto) {
        return patch(API_PREFIX, subsectionsDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}