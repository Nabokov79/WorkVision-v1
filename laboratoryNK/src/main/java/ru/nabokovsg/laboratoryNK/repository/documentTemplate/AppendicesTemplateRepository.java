package ru.nabokovsg.laboratoryNK.repository.documentTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.AppendicesTemplate;

import java.util.Optional;

public interface AppendicesTemplateRepository extends JpaRepository<AppendicesTemplate, Long> {

    AppendicesTemplate findByAppendicesName(String appendicesName);

    Optional<AppendicesTemplate> findByEquipmentTypeId(Long equipmentTypeId);
}