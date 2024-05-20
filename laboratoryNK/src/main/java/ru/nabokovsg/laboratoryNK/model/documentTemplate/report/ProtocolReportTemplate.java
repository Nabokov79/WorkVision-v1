package ru.nabokovsg.laboratoryNK.model.documentTemplate.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.ConclusionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_protocol_templates")
public class ProtocolReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document_type_id")
    private Long documentTypeId;
    @Column(name = "sequential_number")
    private Integer sequentialNumber;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "user_text_after_subtitle")
    private String userTextAfterSubtitle;
    @OneToMany(mappedBy = "protocolReportTemplate", fetch = FetchType.LAZY)
    private Set<SubsectionTemplate> subsectionTemplates;
    @OneToMany(mappedBy = "protocolReportTemplate", fetch = FetchType.LAZY)
    private Set<TableTemplate> tableTemplates;
    @OneToOne
    @JoinColumn(name = "conclusion_template_id", referencedColumnName = "id")
    private ConclusionTemplate conclusionTemplate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "section_template_id")
    @JsonIgnore
    private SectionTemplate sectionTemplate;
}