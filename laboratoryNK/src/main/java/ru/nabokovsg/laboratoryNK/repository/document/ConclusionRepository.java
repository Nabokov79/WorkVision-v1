package ru.nabokovsg.laboratoryNK.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Conclusion;

public interface ConclusionRepository extends JpaRepository<Conclusion, Long> {
}