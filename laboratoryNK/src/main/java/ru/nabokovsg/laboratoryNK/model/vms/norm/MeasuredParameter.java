package ru.nabokovsg.laboratoryNK.model.vms.norm;

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
@Table(name = "measurements_parameters")
public class MeasuredParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "parameter_name")
    private String parameterName;
    @Column(name = "unit_measurement")
    private String unitMeasurement;
    @Column(name = "type_calculation")
    @Enumerated(EnumType.STRING)
    private CalculationType typeCalculation;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "defect_id", nullable = false)
    private Defect defect;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id", nullable = false)
    private ElementRepair elementRepair;

    @Override
    public String toString() {
        return "MeasuredParameter{" +
                "id=" + id +
                ", parameterName='" + parameterName + '\'' +
                ", unitMeasurement='" + unitMeasurement + '\'' +
                ", typeCalculation=" + typeCalculation +
                ", defect=" + defect +
                ", elementRepair=" + elementRepair +
                '}';
    }
}