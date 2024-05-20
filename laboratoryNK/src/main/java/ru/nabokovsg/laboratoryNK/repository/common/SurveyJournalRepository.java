package ru.nabokovsg.laboratoryNK.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.laboratoryNK.model.common.SurveyJournal;

import java.time.LocalDate;

public interface SurveyJournalRepository extends JpaRepository<SurveyJournal, Long> {

    boolean existsByDateAndEquipmentId(LocalDate date, Long equipmentId);
}