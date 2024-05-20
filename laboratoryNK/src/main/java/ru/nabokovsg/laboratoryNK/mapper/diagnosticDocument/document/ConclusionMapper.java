package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Conclusion;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;

@Mapper(componentModel = "spring")
public interface ConclusionMapper {

    Conclusion mapToConclusion(ConclusionTemplate template);
}