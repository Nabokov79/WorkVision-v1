package ru.nabokovsg.laboratoryNK.repository.measurement.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;

public interface DefectMeasurementRepository extends JpaRepository<DefectMeasurement, Long> {
}