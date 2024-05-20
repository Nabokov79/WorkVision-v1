package ru.nabokovsg.laboratoryNK.model.common;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "survey_journals")
public class SurveyJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "branch")
    private String branch;
    @Column(name = "heat_supply_area")
    private String heatSupplyArea;
    @Column(name = "exploitation_region")
    private String exploitationRegion;
    @Column(name = "building")
    private String building;
    @Column(name = "equipment_id")
    private Long equipmentId;
    @Column(name = "equipment_diagnosed")
    private String equipmentDiagnosed;
    @Column(name = "work_type")
    private String workType;
    @Column(name = "task_source")
    private String taskSource;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "chief_id",  nullable = false)
    private LaboratoryEmployee chief;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "journal_employees",
            joinColumns =  {@JoinColumn(name = "journal_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    @ToString.Exclude
    private Set<LaboratoryEmployee> employees;
}