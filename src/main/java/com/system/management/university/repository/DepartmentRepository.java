package com.system.management.university.repository;


import com.system.management.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentRepository extends CrudRepository<Department,Long> {
    Set<Department> findAllByFacultyId(Long id);
}
