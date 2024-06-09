package ru.nabokovsg.laboratoryNK.repository.vms.norm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.norm.ElementRepair;

public interface ElementRepairRepository extends JpaRepository<ElementRepair, Long> {

    ElementRepair findByRepairName(String repairName);
}