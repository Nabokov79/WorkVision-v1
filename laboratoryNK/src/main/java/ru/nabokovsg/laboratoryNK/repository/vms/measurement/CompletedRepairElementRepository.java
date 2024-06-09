package ru.nabokovsg.laboratoryNK.repository.vms.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.vms.measurement.CompletedRepairElement;

public interface CompletedRepairElementRepository extends JpaRepository<CompletedRepairElement, Long> {
}