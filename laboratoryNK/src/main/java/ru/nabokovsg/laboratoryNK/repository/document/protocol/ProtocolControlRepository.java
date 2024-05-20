package ru.nabokovsg.laboratoryNK.repository.document.protocol;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol.ProtocolControl;

public interface ProtocolControlRepository extends JpaRepository<ProtocolControl, Long> {
}