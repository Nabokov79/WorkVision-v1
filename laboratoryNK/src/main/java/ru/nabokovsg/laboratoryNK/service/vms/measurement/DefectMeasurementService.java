package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.DefectMeasurement;

import java.util.List;
import java.util.Set;

public interface DefectMeasurementService {

    ResponseDefectMeasurementDto save(DefectMeasurementDto defectMeasurementDto);

    List<ResponseDefectMeasurementDto> getAll(Long id);

    Set<DefectMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);

    Double getMaxCorrosionValueByPredicate(UltrasonicThicknessMeasurement measurement);
}