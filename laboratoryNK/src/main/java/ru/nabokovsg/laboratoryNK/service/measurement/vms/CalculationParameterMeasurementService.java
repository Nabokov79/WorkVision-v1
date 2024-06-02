package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.model.measurement.CalculationParameterMeasurementBuilder;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;

import java.util.Set;

public interface CalculationParameterMeasurementService {

    Set<CalculationParameterMeasurement> calculation(CalculationParameterMeasurementBuilder builder);
}