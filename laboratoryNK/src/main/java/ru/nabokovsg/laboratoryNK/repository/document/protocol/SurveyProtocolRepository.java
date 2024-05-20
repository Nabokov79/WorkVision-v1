package ru.nabokovsg.laboratoryNK.repository.document.protocol;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.SurveyProtocol;

public interface SurveyProtocolRepository extends JpaRepository<SurveyProtocol, Long> {
}