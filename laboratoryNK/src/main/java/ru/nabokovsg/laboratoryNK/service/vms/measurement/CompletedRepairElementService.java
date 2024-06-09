package ru.nabokovsg.laboratoryNK.service.vms.measurement;

import ru.nabokovsg.laboratoryNK.dto.vms.measurement.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.vms.measurement.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;

import java.util.List;
import java.util.Set;

public interface CompletedRepairElementService {

    ResponseCompletedRepairElementDto save(CompletedRepairElementDto repairDto);

    List<ResponseCompletedRepairElementDto> getAll(Long id);

    Set<CompletedRepairElement> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);
}