package ru.nabokovsg.laboratoryNK.repository.measurement.utm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;

import java.util.Set;

public interface ResultUltrasonicThicknessMeasurementRepository
        extends JpaRepository<ResultUltrasonicThicknessMeasurement, Long> {

    Set<ResultUltrasonicThicknessMeasurement>  findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId
                                                                                    , Long equipmentId);
}