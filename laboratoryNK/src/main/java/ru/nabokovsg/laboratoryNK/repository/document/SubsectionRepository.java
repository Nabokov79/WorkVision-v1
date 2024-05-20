package ru.nabokovsg.laboratoryNK.repository.document;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;

public interface SubsectionRepository extends JpaRepository<Subsection, Long> {
}