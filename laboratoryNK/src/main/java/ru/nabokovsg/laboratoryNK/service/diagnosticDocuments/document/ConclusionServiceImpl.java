package ru.nabokovsg.laboratoryNK.service.diagnosticDocuments.document;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document.ConclusionMapper;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Conclusion;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;
import ru.nabokovsg.laboratoryNK.repository.document.ConclusionRepository;

@Service
@RequiredArgsConstructor
public class ConclusionServiceImpl implements ConclusionService {

    private final ConclusionRepository repository;
    private final ConclusionMapper mapper;

    @Override
    public Conclusion save(ConclusionTemplate template) {
        return repository.save(mapper.mapToConclusion(template));
    }
}