package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.ResponseVisualInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.visualInspection.VisualInspectionDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.VisualInspection;

import java.util.List;
import java.util.Set;

public interface VisualInspectionService {

    ResponseVisualInspectionDto save(VisualInspectionDto inspectionDto);

    ResponseVisualInspectionDto update(VisualInspectionDto inspectionDto);

    List<ResponseVisualInspectionDto> getAll(Long id);

    Set<VisualInspection> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);
}