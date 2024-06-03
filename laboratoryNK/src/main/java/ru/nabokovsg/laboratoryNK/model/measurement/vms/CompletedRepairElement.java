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
    @Column(name = "repair_id")
    private Long repairId;
    @Column(name = "repair_name")
    private String repairName;
    @OneToMany(mappedBy = "completedRepairElement")
    private Set<CalculationParameterMeasurement> parameterMeasurements;
}