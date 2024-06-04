package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.Defect;

import java.util.List;

public interface CalculationParameterMeasurementService {

    void calculation(Defect defect, DefectMeasurement defectMeasurement, List<ParameterMeasurementDto> parameterMeasurementsDto);
}