package ru.nabokovsg.laboratoryNK.repository.documentTemplate.protocol;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol.SurveyProtocolTemplate;

public interface SurveyProtocolTemplateRepository extends JpaRepository<SurveyProtocolTemplate, Long> {

    boolean existsByDocumentTypeIdAndEquipmentTypeId(Long documentTypeId, Long equipmentTypeId);
}