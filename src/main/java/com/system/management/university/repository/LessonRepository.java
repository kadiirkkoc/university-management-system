package com.system.management.university.repository;

import com.system.management.university.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findAllByDepartmentId(Long id);
    List<Lesson> findAllByInstructorId(Long id);

    List<Lesson> findAllByStudentId(Long id);
}
