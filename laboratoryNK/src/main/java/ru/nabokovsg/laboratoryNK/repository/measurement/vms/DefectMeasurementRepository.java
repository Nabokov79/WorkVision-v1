package ru.nabokovsg.laboratoryNK.repository.measurement.vms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;

import java.util.Set;

public interface DefectMeasurementRepository extends JpaRepository<DefectMeasurement, Long> {

    Set<DefectMeasurement> findAllBySurveyJournalId(Long surveyJournalId);

    Set<DefectMeasurement> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}