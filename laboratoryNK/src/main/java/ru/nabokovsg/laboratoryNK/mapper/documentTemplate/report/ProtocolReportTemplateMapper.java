package ru.nabokovsg.laboratoryNK.mapper.documentTemplate.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.report.protocolReport.ShortResponseProtocolReportTemplateDto;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

@Mapper(componentModel = "spring")
public interface ProtocolReportTemplateMapper {

    @Mapping(source = "diagnosticDocumentType.id", target = "documentTypeId")
    @Mapping(source = "protocolDto.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "diagnosticDocumentType.title", target = "title")
    @Mapping(source = "diagnosticDocumentType.subtitle", target = "subtitle")
    @Mapping(source = "conclusionTemplate", target = "conclusionTemplate")
    @Mapping(source = "protocolDto.userTextAfterSubtitle", target = "userTextAfterSubtitle")
    @Mapping(source = "protocolDto.id", target = "id")
    ProtocolReportTemplate mapToProtocolReportTemplate(ProtocolReportTemplateDto protocolDto
                                                     , DiagnosticDocumentType diagnosticDocumentType
                                                     , ConclusionTemplate conclusionTemplate);

    ResponseProtocolReportTemplateDto mapToResponseProtocolReportTemplateDto(ProtocolReportTemplate protocol);

    ShortResponseProtocolReportTemplateDto mapToShortProtocolReportTemplateDto(ProtocolReportTemplate protocol);

    @Mapping(source = "sectionTemplate", target = "sectionTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    ProtocolReportTemplate mapWithSectionTemplate(@MappingTarget ProtocolReportTemplate protocolReportTemplate
                                                               , SectionTemplate sectionTemplate);
}