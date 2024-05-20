package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.MeasuringTool;

public interface MeasuringToolRepository extends JpaRepository<MeasuringTool, Long> {
}