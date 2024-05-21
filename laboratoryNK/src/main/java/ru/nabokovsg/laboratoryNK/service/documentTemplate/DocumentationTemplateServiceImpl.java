package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.DocumentationTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.DocumentationTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.common.DocumentationService;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationTemplateServiceImpl implements DocumentationTemplateService {

    private final DocumentationTemplateRepository repository;
    private final DocumentationTemplateMapper mapper;
    private final StringBuilderService stringBuilderService;
    private final DocumentationService documentationService;

    @Override
    public List<ResponseDocumentationTemplateDto> save(List<DocumentationTemplateDto> templatesDto) {
        Map<Long, Integer> sequentialNumbers = templatesDto.stream()
                                            .collect(Collectors.toMap(DocumentationTemplateDto::getDocumentationId
                                                                    , DocumentationTemplateDto::getSequentialNumber));
        return repository.saveAll(
                documentationService.getAllByIds(new ArrayList<>(sequentialNumbers.keySet()))
                                    .stream()
                                    .map(d -> mapper.mapToDocumentationTemplate(d
                                                                          , sequentialNumbers.get(d.getId())
                                                                          , stringBuilderService.buildDocumentation(d)))
                                    .toList())
                                    .stream()
                                    .map(mapper::mapToResponseDocumentationTemplateDto)
                                    .toList();
    }

    @Override
    public ResponseDocumentationTemplateDto update(DocumentationTemplateDto templateDto) {
        DocumentationTemplate template = repository.findByDocumentationId(templateDto.getId());
        if (repository.existsById(templateDto.getId())) {
            Documentation documentation = documentationService.getById(templateDto.getDocumentationId());
           return mapper.mapToResponseDocumentationTemplateDto(repository.save(
                   mapper.mapToUpdateDocumentationTemplate(template
                                                         , documentation
                                                         , stringBuilderService.buildDocumentation(documentation))));
        }
        throw new NotFoundException(
                String.format("Documentation template with id=%s not found for update", templateDto.getId()));
    }

    @Override
    public void saveWithSubsectionTemplate(SubsectionTemplate template, List<DocumentationTemplateDto> documentations) {
        Map<Long, DocumentationTemplate> templates = repository.findAllById(
                                            documentations.stream()
                                                    .map(DocumentationTemplateDto::getDocumentationId)
                                                    .toList())
                                            .stream()
                                            .collect(Collectors.toMap(DocumentationTemplate::getId, r -> r));
        if (!templates.isEmpty()) {
            repository.saveAll(documentations.stream()
                    .map(d -> mapper.mapToWithSubsectionTemplate(templates.get(d.getDocumentationId())
                                                               , template
                                                               , d.getSequentialNumber()))
                    .toList());
        }
    }

    @Override
    public List<ResponseDocumentationTemplateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseDocumentationTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("Documentation template with id=%s not found for delete", id));
    }
}