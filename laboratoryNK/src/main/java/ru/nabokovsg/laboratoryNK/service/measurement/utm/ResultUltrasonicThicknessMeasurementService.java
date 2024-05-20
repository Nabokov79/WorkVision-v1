package ru.nabokovsg.laboratoryNK.service.measurement.utm;

import ru.nabokovsg.laboratoryNK.model.measurement.utm.ResultUltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;

import java.util.Set;

public interface ResultUltrasonicThicknessMeasurementService {

    void save(UltrasonicThicknessMeasurement measurement);

    Set<ResultUltrasonicThicknessMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId);
}