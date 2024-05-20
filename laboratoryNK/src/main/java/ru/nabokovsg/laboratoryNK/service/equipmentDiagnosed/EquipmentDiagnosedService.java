package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.EquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseEquipmentDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.equipments.ResponseShortEquipmentDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;

import java.util.List;

public interface EquipmentDiagnosedService {

    ResponseShortEquipmentDto save(EquipmentDto equipmentDto);

    ResponseShortEquipmentDto update(EquipmentDto equipmentDto);

    ResponseEquipmentDto get(Long id, Boolean full);

    List<ResponseShortEquipmentDto> getAll(Long id);

    void delete(Long id);

    EquipmentDiagnosed getById(Long id);
}