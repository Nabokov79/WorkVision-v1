package ru.nabokovsg.laboratoryNK.model.documentTemplate.protocol;

import jakarta.persistence.*;
import lombok.*;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.DocumentHeaderTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.SubsectionTemplate;
import ru.nabokovsg.laboratoryNK.model.documentTemplate.TableTemplate;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "protocol_control_templates")
public class ProtocolControlTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document_type_id")
    private Long documentTypeId;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "protocol_control_templates_document_headers",
            joinColumns = {@JoinColumn(name = "protocol_template_id")},
            inverseJoinColumns = {@JoinColumn(name = "document_header_id")})
    @ToString.Exclude
    private Set<DocumentHeaderTemplate> leftHeaderTemplates;
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @OneToMany(mappedBy = "protocolControlTemplate", fetch = FetchType.LAZY)
    private Set<SubsectionTemplate> subsectionTemplates;
    @OneToMany(mappedBy = "protocolControlTemplate", fetch = FetchType.LAZY)
    private Set<TableTemplate> tableTemplates;
}