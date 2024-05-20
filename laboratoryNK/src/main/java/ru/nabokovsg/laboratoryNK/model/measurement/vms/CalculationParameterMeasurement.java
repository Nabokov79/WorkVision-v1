package ru.nabokovsg.laboratoryNK.model.measurement.vms;

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
@Table(name = "result_measurement_parameters")
public class CalculationParameterMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "first_value")
    private Double firstValue;
    @Column(name = "second_value")
    private Double secondValue;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "defect_measurement_id",  nullable = false)
    private DefectMeasurement defectMeasurement;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "completed_repair_id",  nullable = false)
    private CompletedRepairElement completedRepairElement;
}
