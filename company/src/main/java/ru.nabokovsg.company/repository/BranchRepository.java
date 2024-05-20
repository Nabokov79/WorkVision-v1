package ru.nabokovsg.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nabokovsg.company.model.Branch;

import java.util.Set;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Set<Branch> findAllByOrganizationId(Long organizationId);

    Branch findByFullName(String fullName);

    @Query("select b.fullName from Branch b where b.id = ?1")
    String findFullNameById(Long id);
}