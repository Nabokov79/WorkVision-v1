package ru.nabokovsg.laboratoryNK.service.documentTemplate;

import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.ResponseSubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.SubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

import java.util.List;

public interface SubsectionTemplateService {

    ResponseSubsectionTemplateDto save(SubsectionTemplateDto subsectionsDto);

    ResponseSubsectionTemplateDto update(SubsectionTemplateDto subsectionsDto);

    ResponseSubsectionTemplateDto get(Long id);

    void delete(Long id);

    void addProtocolReportTemplate(ProtocolReportTemplate template, List<Long> subsectionTemplatesId);

    void addSectionTemplate(SectionTemplate template, List<Long> subsectionTemplatesId);

    void addProtocolTemplate(SurveyProtocolTemplate template, List<Long> subsectionTemplatesId);

    void addProtocolControlTemplate(ProtocolControlTemplate template, List<Long> subsectionTemplatesId);
}