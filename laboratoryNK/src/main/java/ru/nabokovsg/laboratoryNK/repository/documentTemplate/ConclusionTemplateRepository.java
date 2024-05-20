package ru.nabokovsg.laboratoryNK.repository.documentTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;

import java.util.Optional;

public interface ConclusionTemplateRepository extends JpaRepository<ConclusionTemplate, Long> {

    boolean existsByDocumentTypeId(Long documentTypeId);

    Optional<ConclusionTemplate> findByDocumentTypeId(Long documentTypeId);
}