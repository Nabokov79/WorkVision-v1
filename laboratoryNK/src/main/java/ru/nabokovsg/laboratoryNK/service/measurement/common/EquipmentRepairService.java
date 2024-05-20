package ru.nabokovsg.laboratoryNK.service.measurement.common;

import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.EquipmentRepairDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentRepair.ResponseEquipmentRepairDto;

import java.util.List;

public interface EquipmentRepairService {

    ResponseEquipmentRepairDto save(EquipmentRepairDto repairDto);

    ResponseEquipmentRepairDto update(EquipmentRepairDto repairDto);

    List<ResponseEquipmentRepairDto> getAll(Long id);

    void delete(Long id);
}