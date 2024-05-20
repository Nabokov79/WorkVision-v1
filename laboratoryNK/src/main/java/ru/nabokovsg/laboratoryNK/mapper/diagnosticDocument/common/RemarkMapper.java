package ru.nabokovsg.laboratoryNK.mapper.diagnosticDocument.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.RemarkDto;
import ru.nabokovsg.laboratoryNK.dto.diagnosticDocuments.remark.ResponseRemarkDto;
import ru.nabokovsg.laboratoryNK.model.common.LaboratoryEmployee;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocument;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.Remark;

@Mapper(componentModel = "spring")
public interface RemarkMapper {

    @Mapping(source = "remarkDto.remark", target = "remark")
    @Mapping(source = "employee.employeeId", target = "employeeId")
    @Mapping(source = "employee.initials", target = "initials")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "remarkDto.id", target = "id")
    Remark mapToRemark(RemarkDto remarkDto, LaboratoryEmployee employee, DiagnosticDocument document);


    @Mapping(source = "remark.id", target = "id")
    @Mapping(source = "remark.document.documentType", target = "documentType")
    @Mapping(source = "remark.document.documentNumber", target = "documentNumber")
    @Mapping(source = "remark.remark", target = "remark")
    @Mapping(source = "remark.initials", target = "initials")
    @Mapping(source = "remark.documentCorrected", target = "documentCorrected")
    ResponseRemarkDto mapToRemarkDto(Remark remark);
}