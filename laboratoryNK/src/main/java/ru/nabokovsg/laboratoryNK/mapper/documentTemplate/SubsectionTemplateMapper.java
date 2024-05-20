package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nabokovsg.laboratoryNK.dto.client.BranchDto;
import ru.nabokovsg.laboratoryNK.dto.client.DepartmentDto;
import ru.nabokovsg.laboratoryNK.dto.client.DivisionDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.ResponseSubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.SubsectionTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.ProtocolReportTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.SectionTemplate;

@Mapper(componentModel = "spring")
public interface SubsectionTemplateMapper {

    SubsectionTemplate mapToSubsectionTemplate(SubsectionTemplateDto subsectionDto);

    @Mapping(source = "tableTemplate", target = "tableTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "protocolReportTemplate", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapWithTableTemplate(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                         , TableTemplate tableTemplate);

    @Mapping(source = "protocolReportTemplate", target = "protocolReportTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "sectionTemplate", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapWithProtocolReportTemplate(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                                  , ProtocolReportTemplate protocolReportTemplate);

    @Mapping(source = "division", target = "division")
    SubsectionTemplate mapWithDivisionContact(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                           , String division);

    @Mapping(source = "sectionTemplate", target = "sectionTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapWithSectionTemplate(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                           , SectionTemplate sectionTemplate);

    @Mapping(source = "protocolTemplate", target = "surveyProtocolTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapWithProtocolTemplate(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                            , SurveyProtocolTemplate protocolTemplate);

    @Mapping(source = "protocolControlTemplate", target = "protocolControlTemplate")
    @Mapping(target = "sequentialNumber", ignore = true)
    @Mapping(target = "id", ignore = true)
    SubsectionTemplate mapWithProtocolControlTemplate(@MappingTarget SubsectionTemplate subsectionsTemplate
                                                                   , ProtocolControlTemplate protocolControlTemplate);

    ResponseSubsectionTemplateDto mapToResponseSubsectionTemplateDto(SubsectionTemplate subsection);

    DivisionDto mapFromBranch(BranchDto branchDto);

    DivisionDto mapFromDepartment(DepartmentDto departmentDto);
}