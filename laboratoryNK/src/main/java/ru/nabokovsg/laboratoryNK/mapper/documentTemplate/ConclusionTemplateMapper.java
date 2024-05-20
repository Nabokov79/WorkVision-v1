package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.conclusion.ResponseConclusionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;

@Mapper(componentModel = "spring")
public interface ConclusionTemplateMapper {

    ConclusionTemplate mapToConclusionTemplate(ConclusionTemplateDto conclusionDto);

    ResponseConclusionTemplateDto mapToResponseConclusionTemplateDto(ConclusionTemplate conclusion);
}