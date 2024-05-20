package ru.nabokovsg.laboratoryNK.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentEquipmentPassport;

public interface DocumentEquipmentPassportRepository extends JpaRepository<DocumentEquipmentPassport, Long> {
}