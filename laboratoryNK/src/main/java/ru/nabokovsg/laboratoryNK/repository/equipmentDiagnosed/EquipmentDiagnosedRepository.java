package ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentDiagnosed;

import java.util.Set;

public interface EquipmentDiagnosedRepository extends JpaRepository<EquipmentDiagnosed, Long> {

    Set<EquipmentDiagnosed> findAllByBuildingId(Long buildingId);
}