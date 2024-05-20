package ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentElement;

import java.util.Set;

public interface EquipmentElementRepository extends JpaRepository<EquipmentElement, Long> {

    Set<EquipmentElement> findAllByEquipmentDiagnosedId(Long id);
}