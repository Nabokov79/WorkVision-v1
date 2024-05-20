package ru.nabokovsg.laboratoryNK.repository.documentTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.MeasuringToolTemplate;

public interface MeasuringToolTemplateRepository extends JpaRepository<MeasuringToolTemplate, Long> {

    boolean existsByTollAndModel(String toll, String model);

    MeasuringToolTemplate findByMeasuringToolId(Long measuringToolId);
}