package ru.nabokovsg.laboratoryNK.service.common;

import ru.nabokovsg.laboratoryNK.dto.client.AddressDto;
import ru.nabokovsg.laboratoryNK.dto.client.BuildingDto;
import ru.nabokovsg.laboratoryNK.dto.client.DivisionDto;
import ru.nabokovsg.laboratoryNK.dto.client.EmployeeDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.DivisionDataDto;
import ru.nabokovsg.laboratoryNK.dto.laboratoryCertificate.LaboratoryCertificateDto;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;

import java.time.LocalDate;
import java.util.List;

public interface StringBuilderService {

    String buildInitials(EmployeeDto employee);

    String buildBuilding(BuildingDto building);

    String buildEquipmentDiagnosed(EquipmentDiagnosed equipment);

    String buildDiagnosticDocumentType(DiagnosticDocumentType diagnosticDocumentType);

    String buildMeasuringToolTemplate(MeasuringTool measuringTool);

    String buildDocumentation(Documentation documentation);

    String buildDivision(DivisionDataDto param, DivisionDto division, List<LaboratoryCertificateDto> certificates);

    String buildCertificate(List<LaboratoryCertificateDto> certificates);

    String buildAddress(AddressDto address);

    String buildEmployeeContacts(EmployeeDto employeeDto);

    String numberAndDate(LocalDate date, Integer documentNumber);

    String buildInstallationLocation(String installationLocation, String building, String buildingType);
}