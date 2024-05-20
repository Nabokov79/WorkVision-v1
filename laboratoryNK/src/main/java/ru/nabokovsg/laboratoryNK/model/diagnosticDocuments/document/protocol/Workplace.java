package ru.nabokovsg.laboratoryNK.model.diagnosticDocuments.document.protocol;

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
@Table(name = "Workplace")
public class Workplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "branch")
    private String branch;
    @Column(name = "area")
    private String heatSupplyArea;
    @Column(name = "region")
    private String exploitationRegion;
    @Column(name = "building")
    private String building;
    @Column(name = "address")
    private String address;
}