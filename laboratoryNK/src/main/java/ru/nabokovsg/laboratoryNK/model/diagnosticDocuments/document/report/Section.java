package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.report;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.DocumentEquipmentPassport;
import ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.Subsection;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "section_name")
    private String sectionName;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<DocumentEquipmentPassport> equipmentPassports;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<Subsection> subsection;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<ProtocolReport> protocolReport;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id",  nullable = false)
    private Report report;
}