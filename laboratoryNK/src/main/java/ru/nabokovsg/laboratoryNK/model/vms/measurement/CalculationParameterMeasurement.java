package ru.nabokovsg.laboratoryNK.model.vms.measurement;

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
@Table(name = "calculation_parameters")
public class CalculationParameterMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private Integer number;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "first_value")
    private Double firstValue;
    @Column(name = "second_value")
    private Double secondValue;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @Column(name = "direction")
    private String direction;
    @ManyToOne
    @JoinColumn(name = "defect_measurement_id",  nullable = false)
    private DefectMeasurement defectMeasurement;
    @ManyToOne
    @JoinColumn(name = "completed_repair_id",  nullable = false)
    private CompletedRepairElement completedRepairElement;

    @Override
    public String toString() {
        return "CalculationParameterMeasurement{" +
                "id=" + id +
                ", number=" + number +
                ", sequentialNumber=" + sequentialNumber +
                ", parameterName='" + parameterName + '\'' +
                ", firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                ", direction='" + direction + '\'' +
                ", defectMeasurement=" + defectMeasurement +
                ", completedRepairElement=" + completedRepairElement +
                '}';
    }
}
