package ru.nabokovsg.laboratoryNK.service.documentTemplate.protocol;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.ShortResponseSurveyProtocolTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.surveyProtocol.SurveyProtocolTemplateDto;

import java.util.List;

public interface SurveyProtocolTemplateService {

    ShortResponseSurveyProtocolTemplateDto save(SurveyProtocolTemplateDto protocolDto);

    ShortResponseSurveyProtocolTemplateDto update(SurveyProtocolTemplateDto protocolDto);

    ResponseSurveyProtocolTemplateDto get(Long id);

   List<ShortResponseSurveyProtocolTemplateDto> getAll();

    void delete(Long id);
}