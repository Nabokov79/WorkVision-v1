package ru.nabokovsg.laboratoryNK.repository.documentTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DivisionType;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;

import java.util.Set;

public interface DocumentHeaderTemplateRepository extends JpaRepository<DocumentHeaderTemplate, Long> {

    Set<DocumentHeaderTemplate> findAllByDocumentTypeId(Long documentTypeId);

    DocumentHeaderTemplate findByDocumentTypeIdAndDivisionType(Long documentTypeId, DivisionType divisionType);
}