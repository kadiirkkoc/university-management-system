package com.system.management.university.service.abstracts;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.dtos.LessonDTO;

import java.util.List;

public interface LessonService {

    List<LessonDTO> getAll();
    LessonDTO getById(Long id);

    void addLesson(LessonDTO lessonDTO);

    void update(Long id, LessonDTO lessonDTO);

    void delete(Long id);
}
