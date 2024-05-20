package ru.nabokovsg.laboratoryNK.repository.measurement.gm;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.measurement.gm.PointDifference;

import java.util.Set;

public interface PointDifferenceRepository extends JpaRepository<PointDifference, Long> {

    Set<PointDifference> findAllBySurveyJournalIdAndEquipmentId(Long surveyJournalId, Long equipmentId);
}