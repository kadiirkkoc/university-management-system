package com.system.management.university.service.abstracts;

import com.system.management.university.dtos.InstructorDTO;

import java.util.List;

public interface InstructorService {
    List<InstructorDTO> getAll();
    InstructorDTO getById(Long id);

    void add(InstructorDTO instructorDTO);

    void update(Long id, InstructorDTO instructorDTO);

    void delete(Long id);
}
