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
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.RegulatoryDocumentationTemplateRepository;
import ru.nabokovsg.laboratoryNK.service.common.StringBuilderService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegulatoryDocumentationTemplateServiceImpl implements RegulatoryDocumentationTemplateService {

    private final RegulatoryDocumentationTemplateRepository repository;
    private final DocumentationTemplateMapper mapper;
    private final StringBuilderService stringBuilderService;

    @Override
    public void save(Documentation documentation) {
        repository.save(
                mapper.mapToRegulatoryDocumentationTemplate(documentation
                                                          , stringBuilderService.buildDocumentation(documentation))
        );
    }

    @Override
    public void update(Documentation documentation) {
        DocumentationTemplate template = repository.findByDocumentationId(documentation.getId());
        if (template != null) {
            repository.save(
                    mapper.mapToUpdateRegulatoryDocumentationTemplate(template
                                                               , documentation
                                                               , stringBuilderService.buildDocumentation(documentation))
            );
        }
    }

    @Override
    public void saveWithSubsectionTemplate(SubsectionTemplate template, List<DocumentationTemplateDto> documentations) {
        Map<Long, DocumentationTemplate> templates = repository.findAllById(
                                            documentations.stream()
                                                    .map(DocumentationTemplateDto::getTemplateId)
                                                    .toList())
                                            .stream()
                                            .collect(Collectors.toMap(DocumentationTemplate::getId, r -> r));
        if (!templates.isEmpty()) {
            repository.saveAll(documentations.stream()
                    .map(d -> mapper.mapToWithSubsectionTemplate(templates.get(d.getTemplateId())
                                                               , template
                                                               , d.getSequentialNumber()))
                    .toList());
        }
    }

    @Override
    public List<ResponseDocumentationTemplateDto> getAll() {
        return repository.findAll()
                         .stream()
                         .map(mapper::mapToResponseRegulatoryDocumentationTemplateDto)
                         .toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                String.format("RegulatoryDocumentation template with id=%s not found for delete", id)
        );
    }
}