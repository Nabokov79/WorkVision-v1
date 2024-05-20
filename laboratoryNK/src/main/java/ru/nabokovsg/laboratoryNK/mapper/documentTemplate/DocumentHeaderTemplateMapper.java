package ru.nabokovsg.laboratoryNK.mapper.documentTemplate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.client.*;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.DocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.documentHeader.ResponseDocumentHeaderTemplateDto;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DivisionType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;

@Mapper(componentModel = "spring")
public interface DocumentHeaderTemplateMapper {

    @Mapping(source = "headerDto.id", target = "id")
    @Mapping(source = "headerDto.documentTypeId", target = "documentTypeId")
    @Mapping(source = "divisionType", target = "divisionType")
    @Mapping(source = "division", target = "division")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "certificate", target = "certificate")
    @Mapping(source = "contacts", target = "contacts")
    DocumentHeaderTemplate mapToDocumentHeader(DocumentHeaderTemplateDto headerDto
                                     , DivisionType divisionType
                                     , String division
                                     , String address
                                     , String certificate
                                     , String contacts);
    ResponseDocumentHeaderTemplateDto mapToResponseDocumentHeaderDto(DocumentHeaderTemplate documentHeader);

    DivisionDto mapFromOrganization(OrganizationDto organization);

    DivisionDto mapFromBranch(BranchDto branch);

    DivisionDto mapFromDepartment(DepartmentDto department);

    DivisionDto mapFromHeatSupplyAreaDto(HeatSupplyAreaDto heatSupplyArea);

    DivisionDto mapFromExploitationRegionDto(ExploitationRegionDto exploitationRegion);
}