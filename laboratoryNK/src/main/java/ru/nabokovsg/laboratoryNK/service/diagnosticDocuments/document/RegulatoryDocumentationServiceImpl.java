package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.RegulatoryDocumentationMapper;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.RegulatoryDocumentationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegulatoryDocumentationServiceImpl implements RegulatoryDocumentationService {

    private final RegulatoryDocumentationRepository repository;
    private final RegulatoryDocumentationMapper mapper;

    @Override
    public void save(Subsection subsection, List<DocumentationTemplate> documentationTemplate) {
        repository.saveAll(documentationTemplate.stream()
                                                .map(d -> mapper.mapToRegulatoryDocumentation(d, subsection))
                                                .toList());
    }
}