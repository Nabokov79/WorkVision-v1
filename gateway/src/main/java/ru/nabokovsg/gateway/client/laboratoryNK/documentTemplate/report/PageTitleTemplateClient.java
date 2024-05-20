package ru.nabokovsg.gateway.client.laboratoryNK.documentTemplate.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.BaseClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.documentTemplate.report.pageTitle.UpdatePageTitleTemplateDto;

@Service
public class PageTitleTemplateClient extends BaseClient {

    private static final String API_PREFIX = "/template/title-page";
    private static final String DELIMITER = "/";

    @Autowired
    public PageTitleTemplateClient(@Value("${laboratoryNK-server.url}") String baseUrl) {
        super(WebClient.builder()
                .baseUrl(baseUrl)
                .build());
    }

    public Mono<Object> save(NewPageTitleTemplateDto pageTitleDto) {
        return post(API_PREFIX, pageTitleDto);
    }

    public Mono<Object> update(UpdatePageTitleTemplateDto pageTitleDto) {
        return patch(API_PREFIX, pageTitleDto);
    }

    public Mono<Object> get(Long id) {
        return get(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }

    public Mono<String> delete(Long id) {
        return delete(String.join(DELIMITER, API_PREFIX, String.valueOf(id)));
    }
}