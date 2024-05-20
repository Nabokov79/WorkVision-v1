package ru.nabokovsg.laboratoryNK.service.measurement.vms;

import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.CompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.vms.completedRepairElement.ResponseCompletedRepairElementDto;
import ru.nabokovsg.laboratoryNK.model.measurement.vms.CompletedRepairElement;

import java.util.List;
import java.util.Set;

public interface CompletedRepairElementService {

    ResponseCompletedRepairElementDto save(CompletedRepairElementDto repairDto);

    ResponseCompletedRepairElementDto update(CompletedRepairElementDto repairDto);

   List<ResponseCompletedRepairElementDto> getAll(Long id);

    Set<CompletedRepairElement> getAllByIds(Long surveyJournalId, Long equipmentId);

    void delete(Long id);
}