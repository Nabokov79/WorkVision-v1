package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.DefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.defectMeasurement.ResponseDefectMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.utm.UltrasonicThicknessMeasurement;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.DefectMeasurement;

import java.util.List;
import java.util.Set;

public interface DefectMeasurementService {

    ResponseDefectMeasurementDto save(DefectMeasurementDto defectMeasurementDto);

    ResponseDefectMeasurementDto update(DefectMeasurementDto defectMeasurementDto);

    List<ResponseDefectMeasurementDto> getAll(Long id);

    Set<DefectMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);

    Double getMaxCorrosionValueByPredicate(UltrasonicThicknessMeasurement measurement);
}