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
@Table(name = "calculating_um_thickness")
public class ResultUltrasonicThicknessMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "element_id")
    private Long elementId;
    @Column(name = "element_name")
    private String elementName;
    @Column(name = "part_element_id")
    private Long partElementId;
    @Column(name = "part_name")
    private String partName;
    @Column(name = "diameter")
    private Integer diameter;
    @Column(name = "measurement_number")
    private Integer measurementNumber;
    @Column(name = "min_measurement_value")
    private Double minMeasurementValue;
    @Column(name = "max_measurement_value")
    private Double maxMeasurementValue;
    @Column(name = "max_corrosion")
    private Double maxCorrosion;
    @Column(name = "residual_thickness")
    private Double residualThickness;
    @Column(name = "min_acceptable_value")
    private Double minAcceptableValue;
    @Column(name = "acceptable_value")
    private Boolean acceptableValue;
    @Column(name = "invalid_value")
    private Boolean invalidValue;
    @Column(name = "approaching_invalid_value")
    private Boolean approachingInvalidValue;
    @Column(name = "reached_invalid_value")
    private Boolean reachedInvalidValue;
    @Column(name = "no_standard")
    private Boolean noStandard;
}