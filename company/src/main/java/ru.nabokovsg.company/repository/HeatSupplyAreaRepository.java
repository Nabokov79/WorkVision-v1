package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.company.model.HeatSupplyArea;

import java.util.Set;

public interface HeatSupplyAreaRepository extends JpaRepository<HeatSupplyArea, Long> {

    HeatSupplyArea findByFullName(String fullName);

    Set<HeatSupplyArea> findAllByBranchId(Long branchId);

    @Query("select h.fullName from HeatSupplyArea h where h.id = ?1")
    String findFullNameById(Long id);
}