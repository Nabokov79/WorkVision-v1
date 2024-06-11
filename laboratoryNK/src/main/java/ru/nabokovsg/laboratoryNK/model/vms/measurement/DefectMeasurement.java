package ru.nabokovsg.laboratoryNK.model.vms.measurement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.vms.EquipmentSurvey;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "defect_measurements")
public class DefectMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "defect_id")
    private Long defectId;
    @Column(name = "defect_name")
    private String defectName;
    @Column(name = "not_meet_requirements")
    private Boolean notMeetRequirements;
    @Column(name = "use_calculate_thickness")
    private Boolean useCalculateThickness;
    @OneToMany(mappedBy = "defectMeasurement")
    private Set<CalculationParameterMeasurement> parameterMeasurements;
    @ManyToOne
    @JoinColumn(name = "equipment_survey_id",  nullable = false)
    private EquipmentSurvey equipmentSurvey;
}