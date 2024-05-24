package ru.nabokovsg.laboratoryNK.repository.documentTemplate.report;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.report.PageTitleTemplate;

import java.util.Optional;

public interface PageTitleTemplateRepository extends JpaRepository<PageTitleTemplate, Long> {

    Optional<PageTitleTemplate> findByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId);

    boolean existsByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId);
}