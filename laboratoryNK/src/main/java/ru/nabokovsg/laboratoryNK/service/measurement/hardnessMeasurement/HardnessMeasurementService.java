package ru.nabokovsg.laboratoryNK.service.measurement.hardnessMeasurement;

import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.HardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.hardnessMeasurement.ResponseHardnessMeasurementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.HardnessMeasurement;

import java.util.List;
import java.util.Set;

public interface HardnessMeasurementService {

    List<ResponseHardnessMeasurementDto> save(HardnessMeasurementDto measurementDto);

    List<ResponseHardnessMeasurementDto> update(HardnessMeasurementDto measurementDto);

    List<ResponseHardnessMeasurementDto> getAll(Long id);

    Set<HardnessMeasurement> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);
}