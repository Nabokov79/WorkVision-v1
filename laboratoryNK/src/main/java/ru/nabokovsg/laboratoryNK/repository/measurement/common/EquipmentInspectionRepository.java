package ru.nabokovsg.laboratoryNK.repository.measurement.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.common.EquipmentInspection;

import java.util.Set;

public interface EquipmentInspectionRepository extends JpaRepository<EquipmentInspection, Long> {

    Set<EquipmentInspection> findAllByEquipmentDiagnosedId(Long equipmentDiagnosedId);
}