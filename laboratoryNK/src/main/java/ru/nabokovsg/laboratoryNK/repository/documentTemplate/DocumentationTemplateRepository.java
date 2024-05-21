package ru.nabokovsg.laboratoryNK.repository.documentTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentationTemplate;

public interface DocumentationTemplateRepository extends JpaRepository<DocumentationTemplate, Long> {

    DocumentationTemplate findByDocumentationId(Long documentationId);
}