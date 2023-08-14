package com.system.management.university.service.abstracts;

import com.system.management.university.dtos.FacultyDTO;

import java.util.List;

public interface FacultyService {

    List<FacultyDTO> getAll();

    FacultyDTO getFacultyById(Long id);

    void addFaculty(FacultyDTO facultyDTO);

    void updateFaculty(Long id, FacultyDTO facultyDTO);

    void deleteFaculty(Long id);
}
