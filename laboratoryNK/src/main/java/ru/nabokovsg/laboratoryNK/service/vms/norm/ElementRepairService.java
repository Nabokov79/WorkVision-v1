package ru.nabokovsg.laboratoryNK.service.vms.norm;

import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ResponseElementRepairDto;
import ru.nabokovsg.laboratoryNK.dto.vms.norm.elementRepair.ResponseShortElementRepairDto;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;

import java.util.List;

public interface ElementRepairService {

    ResponseElementRepairDto save(ElementRepairDto repairDto);

    ResponseElementRepairDto update(ElementRepairDto repairDto);

    ResponseElementRepairDto get(Long id);

    List<ResponseShortElementRepairDto> getAll();

    void delete(Long id);

    ElementRepair getById(Long id);
}