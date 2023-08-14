package com.system.management.university.service.abstracts;


import com.system.management.university.dtos.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAll();
    DepartmentDTO getById(Long id);

    void addDepartment(DepartmentDTO departmentDTO);

    void update(Long id,DepartmentDTO departmentDTO);

    void delete(Long id);

}
