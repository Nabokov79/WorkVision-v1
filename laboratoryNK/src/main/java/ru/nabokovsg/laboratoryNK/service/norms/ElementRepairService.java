package ru.nabokovsg.laboratoryNK.service.norms;

import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.norms.elementRepair.ResponseShortElementRepairDto;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;

import java.util.List;

public interface ElementRepairService {

    ResponseElementRepairDto save(ElementRepairDto repairDto);

    ResponseElementRepairDto update(ElementRepairDto repairDto);

    ResponseElementRepairDto get(Long id);

    List<ResponseShortElementRepairDto> getAll();

    void delete(Long id);

    ElementRepair getById(Long id);
}