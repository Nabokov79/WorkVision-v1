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
@Table(name = "geodesic_measurements")
public class GeodesicMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "survey_journal_id")
    private Long surveyJournalId;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "number_measurement_location")
    private Integer numberMeasurementLocation;
    @Column(name = "reference_point_value")
    private Integer referencePointValue;
    @Column(name = "control_point_value")
    private Integer controlPointValue;
    @Column(name = "transition_value")
    private Integer transitionValue;
}