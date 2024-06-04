package ru.nabokovsg.laboratoryNK.model.measurement.vms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "completed_element_repairs")
public class CompletedRepairElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "repair_id")
    private Long repairId;
    @Column(name = "repair_name")
    private String repairName;
    @OneToMany(mappedBy = "completedRepairElement")
    private Set<CalculationParameterMeasurement> parameterMeasurements;
    @ManyToOne
    @JoinColumn(name = "vm_id",  nullable = false)
    private VMSurvey vmSurvey;
}