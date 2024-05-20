package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentTable;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.ProtocolReport;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report.Section;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionMapper {

    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.subsectionName", target = "subsectionName")
    @Mapping(source = "template.userText", target = "userText")
    @Mapping(source = "template.division", target = "division")
    @Mapping(source = "section", target = "section")
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "measuringTools", ignore = true)
    @Mapping(target = "protocolReport", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "id", ignore = true)
    Subsection mapToSubsection(SubsectionTemplate template, Section section);

    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.subsectionName", target = "subsectionName")
    @Mapping(source = "template.userText", target = "userText")
    @Mapping(source = "template.division", target = "division")
    @Mapping(source = "protocolReport", target = "protocolReport")
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "measuringTools", ignore = true)
    @Mapping(target = "section", ignore = true)
    @Mapping(target = "table", ignore = true)
    @Mapping(target = "id", ignore = true)
    Subsection mapWithProtocolReport(SubsectionTemplate template, ProtocolReport protocolReport);

    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.subsectionName", target = "subsectionName")
    @Mapping(source = "template.userText", target = "userText")
    @Mapping(source = "template.division", target = "division")
    @Mapping(source = "table", target = "table")
    @Mapping(source = "section", target = "section")
    @Mapping(target = "documentation", ignore = true)
    @Mapping(target = "measuringTools", ignore = true)
    @Mapping(target = "protocolReport", ignore = true)
    @Mapping(target = "id", ignore = true)
    Subsection mapWithTable(SubsectionTemplate template, Section section, DocumentTable table);
}