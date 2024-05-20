package ru.nabokovsg.laboratoryNK.repository.documentTemplate.protocol;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.ProtocolControlTemplate;

public interface ProtocolControlTemplateRepository extends JpaRepository<ProtocolControlTemplate, Long> {

    boolean existsByDocumentTypeId(Long documentTypeId);
}