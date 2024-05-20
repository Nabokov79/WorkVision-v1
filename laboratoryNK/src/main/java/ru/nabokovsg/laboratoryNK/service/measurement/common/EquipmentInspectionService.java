package ru.nabokovsg.laboratoryNK.service.measurement.common;

import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.EquipmentInspectionDto;
import ru.nabokovsg.laboratoryNK.dto.measurement.common.equipmentInspection.ResponseEquipmentInspectionDto;

import java.util.List;

public interface EquipmentInspectionService {

    ResponseEquipmentInspectionDto save(EquipmentInspectionDto inspectionDto);

    ResponseEquipmentInspectionDto update(EquipmentInspectionDto inspectionDto);

    List<ResponseEquipmentInspectionDto> getAll(Long id);

    void delete(Long id);
}