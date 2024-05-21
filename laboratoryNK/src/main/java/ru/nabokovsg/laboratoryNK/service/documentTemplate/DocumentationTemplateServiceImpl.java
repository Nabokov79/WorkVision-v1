package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.DocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentationTemplate.ResponseDocumentationTemplateDto;
import ru.nabokovsg.laboratoryNK.exceptions.NotFoundException;
import ru.nabokovsg.laboratoryNK.mapper.documentTemplate.DocumentationTemplateMapper;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.repository.documentTemplate.DocumentationTemplateRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentationTemplateServiceImpl implements DocumentationTemplateService {

    private final DocumentationTemplateRepository repository;
    private final DocumentationTemplateMapper mapper;

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