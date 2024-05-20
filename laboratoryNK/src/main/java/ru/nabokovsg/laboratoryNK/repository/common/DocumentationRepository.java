package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.Documentation;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    Documentation findByTitle(String title);
}