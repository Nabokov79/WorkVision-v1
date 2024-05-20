package ru.nabokovsg.laboratoryNK.repository.norms;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.norms.ElementRepair;

public interface ElementRepairRepository extends JpaRepository<ElementRepair, Long> {

    ElementRepair findByRepairName(String repairName);
}