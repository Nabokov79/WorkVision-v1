package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.parameterMeasurement.ParameterMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;
import ru.nabokovsg.laboratoryNK.model.norms.MeasuredParameter;
import ru.nabokovsg.laboratoryNK.model.norms.TypeCalculation;

import java.util.List;
import java.util.Set;

public interface ParameterMeasurementService {

    Set<CalculationParameterMeasurement> save(TypeCalculation typeCalculation
                                            , Set<MeasuredParameter> measuredParameters
                                            , Set<CalculationParameterMeasurement> parameterMeasurements
                                            , List<ParameterMeasurementDto> parameterMeasurementsDto);

    Set<CalculationParameterMeasurement> update(TypeCalculation typeCalculation
            , Set<MeasuredParameter> measuredParameters
            , Set<CalculationParameterMeasurement> parameterMeasurements
            , List<ParameterMeasurementDto> parameterMeasurementsDto);
}