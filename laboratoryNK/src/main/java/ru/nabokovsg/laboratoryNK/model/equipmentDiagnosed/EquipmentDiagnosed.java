package ru.nabokovsg.laboratoryNK.model.equipmentDiagnosed;

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
@Table(name = "equipment_diagnosed")
public class EquipmentDiagnosed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="equipment_type_id", nullable=false)
    private EquipmentType equipmentType;
    @Column(name = "building_id")
    private Long buildingId;
    @Column(name = "equipment_name")
    private String equipmentName;
    @Column(name = "stationary_number")
    private Integer stationaryNumber;
    @Column(name = "volume")
    private Integer volume;
    @Column(name = "equipment_full")
    private Boolean full;
    @Column(name = "old")
    private Boolean old;
    @Column(name = "model")
    private String model;
    @Column(name = "geodesy_locations")
    private Integer geodesyLocations;
    @OneToMany(mappedBy = "equipmentDiagnosed", fetch = FetchType.LAZY)
    private Set<EquipmentElement> elements;
    @OneToMany(mappedBy = "equipmentDiagnosed", fetch = FetchType.LAZY)
    private Set<EquipmentPassport> passport;
}