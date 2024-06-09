package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.norm.Defect;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;

import java.util.List;
import java.util.Set;

public interface ParameterMeasurementService {

    Set<CalculationParameterMeasurement> saveForDefectMeasurement(Defect defect
                                                            , DefectMeasurement defectMeasurement
                                                            , List<ParameterMeasurementDto> parameterMeasurementsDto);

    Set<CalculationParameterMeasurement> saveForCompletedRepair(ElementRepair elementRepair
                                                              , CompletedRepairElement completedRepair
                                                              , List<ParameterMeasurementDto> parameterMeasurementsDto);
}