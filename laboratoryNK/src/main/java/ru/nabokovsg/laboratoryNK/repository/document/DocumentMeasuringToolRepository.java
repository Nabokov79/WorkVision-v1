package ru.nabokovsg.laboratoryNK.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentMeasuringTool;

public interface DocumentMeasuringToolRepository extends JpaRepository<DocumentMeasuringTool, Long> {
}