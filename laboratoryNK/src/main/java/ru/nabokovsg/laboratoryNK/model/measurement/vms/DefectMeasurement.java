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
@Table(name = "defect_measurements")
public class DefectMeasurement {

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

    @Override
    public String toString() {
        return "DefectMeasurement{" +
                "id=" + id +
                ", surveyJournalId=" + surveyJournalId +
                ", equipmentId=" + equipmentId +
                ", elementId=" + elementId +
                ", elementName='" + elementName + '\'' +
                ", partElementId=" + partElementId +
                ", partName='" + partName + '\'' +
                ", defectId=" + defectId +
                ", defectName='" + defectName + '\'' +
                ", notMeetRequirements=" + notMeetRequirements +
                ", useCalculateThickness=" + useCalculateThickness +
                ", parameterMeasurements=" + parameterMeasurements +
                '}';
    }
}