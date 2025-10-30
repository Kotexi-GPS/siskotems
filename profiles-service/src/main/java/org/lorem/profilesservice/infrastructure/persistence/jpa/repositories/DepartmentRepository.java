package org.lorem.profilesservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.profilesservice.domain.model.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

