package ru.nabokovsg.laboratoryNK.model.measurement.utm;

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
@Table(name = "ultrasonic_thickness_measurements")
public class UltrasonicThicknessMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "measurement_number")
    private Integer measurementNumber;
    @Column(name = "min_measurement_value")
    private Double minMeasurementValue;
    @Column(name = "max_measurement_value")
    private Double maxMeasurementValue;
}