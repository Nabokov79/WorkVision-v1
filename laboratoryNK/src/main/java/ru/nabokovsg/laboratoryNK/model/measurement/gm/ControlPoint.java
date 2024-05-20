package ru.nabokovsg.laboratoryNK.model.measurement.gm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "control_points")
public class ControlPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "place_number")
    private Integer placeNumber;
    @Column(name = "calculated_height")
    private Integer calculatedHeight;
    @Column(name = "deviation")
    private Integer deviation;
}