package ru.nabokovsg.laboratoryNK.service.common;

import org.springframework.stereotype.Component;
import ru.nabokovsg.laboratoryNK.dto.client.AddressDto;
import ru.nabokovsg.laboratoryNK.dto.client.BuildingDto;
import ru.nabokovsg.laboratoryNK.dto.client.DivisionDto;
import ru.nabokovsg.laboratoryNK.dto.client.EmployeeDto;
import ru.nabokovsg.laboratoryNK.dto.documentTemplate.subsectionTemplate.DivisionDataDto;
import ru.nabokovsg.laboratoryNK.dto.laboratoryCertificate.LaboratoryCertificateDto;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.common.DiagnosticDocumentType;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.ConstantMonth;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class StringBuilderServiceImpl extends ConstantMonth implements StringBuilderService {

    @Override
    public String buildInitials(EmployeeDto employee) {
        return String.join(". ", String.join(".", String.valueOf(employee.getName().charAt(0))
                                        , String.valueOf(employee.getPatronymic().charAt(0))).toUpperCase()
                                        , employee.getSurname());
    }

    @Override
    public String buildBuilding(BuildingDto building) {
        String workPlace = building.getBuildingType();
        if (building.getLogin() != null) {
            workPlace = String.join(" ", workPlace, "«", building.getLogin(), "»");
        }
        return String.join(", ", workPlace, buildAddress(building.getAddress()));
    }

    @Override
    public String buildEquipmentDiagnosed(EquipmentDiagnosed equipment) {
        String equipmentDiagnosed = equipment.getEquipmentName();
        if (equipment.getModel() != null) {
            equipmentDiagnosed = String.join(" ", equipmentDiagnosed, equipment.getModel());
        }
        if (equipment.getStationaryNumber() != null) {
            equipmentDiagnosed = String.join("", equipmentDiagnosed, ",");
            equipmentDiagnosed = String.join(" ", equipmentDiagnosed
                                                        , "ст. №"
                                                        , String.valueOf(equipment.getStationaryNumber()));
        }
        if (equipment.getVolume() != null) {
            equipmentDiagnosed = String.join(" ", equipmentDiagnosed
                                                        , "V = "
                                                        , String.valueOf(equipment.getVolume())
                                                        , "м3");
        }
        return equipmentDiagnosed;
    }

    @Override
    public String buildDiagnosticDocumentType(DiagnosticDocumentType diagnosticDocumentType) {
        return String.join(", ", diagnosticDocumentType.getTitle(), diagnosticDocumentType.getSubtitle());
    }

    @Override
    public String buildMeasuringToolTemplate(MeasuringTool measuringTool) {
        return String.join(" ", measuringTool.getToll()
                , "«", measuringTool.getModel(), "»"
                , "зав. №", measuringTool.getWorkNumber()
                , "свидетельство о поверке №", measuringTool.getCertificateNumber());
    }

    @Override
    public String buildDocumentation(Documentation documentation) {
        String string = String.join("", "«", documentation.getTitle(), "»");
        if (documentation.getNumber() != null) {
            string = String.join(" ", documentation.getNumber(), string);
        }
        if (documentation.getView() != null) {
            string = String.join(" ", documentation.getView(), string);
        }
        return string;
    }

    @Override
    public String buildDivision(DivisionDataDto param
                              , DivisionDto division
                              , List<LaboratoryCertificateDto> certificates) {
        String divisionData = String.join(". "
                , getDivisionName(division.getFullName(), param.getUserDivisionName()));
        if (param.getSpecifyAddress()) {
            divisionData = String.join(". ", divisionData, buildAddress(division.getAddress()));
        }
        if (param.getSpecifyCertificate()) {
            divisionData = String.join(". ", divisionData, buildCertificate(certificates));
        }
        return divisionData;
    }

    @Override
    public String buildCertificate(List<LaboratoryCertificateDto> certificates) {
        LaboratoryCertificateDto certificate = certificates.stream()
                .filter(l -> l.getEndDate().isAfter(LocalDate.now()))
                .toList()
                .get(0);
        if (certificate == null) {
            certificate = certificates.stream()
                    .max(Comparator.comparing(LaboratoryCertificateDto::getEndDate))
                    .orElseThrow(NoSuchElementException::new);
        }
        return String.join(" ", certificate.getDocumentType(),
                "№",
                certificate.getLicenseNumber(),
                "от",
                certificate.getStartDate().toString());
    }

    @Override
    public String buildAddress(AddressDto address) {
        String string = String.join(", ", address.getCity()
                , String.join(" ", address.getStreet()
                        , "д.", String.valueOf(address.getHouseNumber())));
        if (address.getBuildingNumber() != null) {
            string = String.join(", ", string, String.join(""
                    , "корп.", String.valueOf(address.getBuildingNumber())));
        }
        if (address.getLetter() != null) {
            string = String.join(", ", string, String.join("", "лит.", address.getLetter()));
        }
        if (address.getIndex() != null) {
            return String.join(", ", String.valueOf(address.getIndex()), string);
        } else {
            return string;
        }
    }

    @Override
    public String buildEmployeeContacts(EmployeeDto employeeDto) {
        return String.join(" "
                , String.join(" ","тел./факс", employeeDto.getPhone(),"/", employeeDto.getFax())
                , String.join(" ", "E-mail:", employeeDto.getEmail()));
    }

    @Override
    public String numberAndDate(LocalDate date, Integer documentNumber) {
        return String.join(" "
                 , "№", String.valueOf(documentNumber)
                         , "от", String.valueOf(date.getDayOfMonth()), get(date), String.valueOf(date.getYear()), "г.");
    }

    @Override
    public String buildInstallationLocation(String installationLocation, String building, String buildingType) {
        String[] workPlace = building.split(",")[0].split(" ");
        if (workPlace[0].equals(buildingType)) {
            workPlace[0] = "котельной";
        }
        return String.join(" ", installationLocation, String.join(" ", workPlace));
    }

    private String getDivisionName(String name, String userDivisionName){
        if (userDivisionName != null) {
            return userDivisionName;
        }
        return name;
    }

}
