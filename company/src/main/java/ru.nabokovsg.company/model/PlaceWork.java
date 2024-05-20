package ru.nabokovsg.company.model;

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
@Table(name = "place_works")
public class PlaceWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;
    @OneToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;
    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @OneToOne
    @JoinColumn(name = "exploitation_region_id", referencedColumnName = "id")
    private ExploitationRegion exploitationRegion;
    @OneToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;
}