package ru.nabokovsg.laboratoryNK.repository.equipmentDiagnosed;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed.EquipmentPassport;

import java.util.Set;

public interface EquipmentPassportRepository extends JpaRepository<EquipmentPassport, Long> {

    Set<EquipmentPassport> findAllByEquipmentDiagnosedId(Long id);
}