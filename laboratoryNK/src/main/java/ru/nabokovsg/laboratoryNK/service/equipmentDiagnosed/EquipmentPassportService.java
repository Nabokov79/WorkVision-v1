package ru.nabokovsg.laboratoryNK.service.equipmentDiagnosed;

import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport.EquipmentPassportDto;
import ru.nabokovsg.laboratoryNK.dto.equipmentDiagnosed.passport.ResponseEquipmentPassportDto;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentPassport;

import java.util.List;
import java.util.Set;

public interface EquipmentPassportService {

    ResponseEquipmentPassportDto save(EquipmentPassportDto passportDto);

    ResponseEquipmentPassportDto update(EquipmentPassportDto passportDto);

    List<ResponseEquipmentPassportDto> getAll(Long id);

    Set<EquipmentPassport> getAllById(Long id);

    void delete(Long id);
}