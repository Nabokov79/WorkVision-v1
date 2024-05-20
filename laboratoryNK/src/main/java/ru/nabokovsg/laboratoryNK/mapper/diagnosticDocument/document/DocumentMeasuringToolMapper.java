package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.document;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentMeasuringTool;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;

@Mapper(componentModel = "spring")
public interface DocumentMeasuringToolMapper {

    @Mapping(source = "template.sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "template.measuringTool", target = "measuringTool")
    @Mapping(source = "subsection", target = "subsection")
    @Mapping(target = "id", ignore = true)
    DocumentMeasuringTool mapFromMeasuringToolTemplate(MeasuringToolTemplate template
                                                     , Subsection subsection);

    @Mapping(source = "sequentialNumber", target = "sequentialNumber")
    @Mapping(source = "measuringTool", target = "measuringTool")
    @Mapping(source = "subsection", target = "subsection")
    @Mapping(target = "id", ignore = true)
    DocumentMeasuringTool mapToDocumentMeasuringTool(Integer sequentialNumber
                                                   , String measuringTool
                                                   , Subsection subsection);
}