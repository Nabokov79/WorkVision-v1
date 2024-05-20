package ru.nabokovsg.laboratoryNK.repository.measurement.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CalculationParameterMeasurement;

public interface ParameterMeasurementServiceRepository extends JpaRepository<CalculationParameterMeasurement, Long> {
}