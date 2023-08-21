package com.system.management.university.service.business;

import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.dtos.StudentDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Department;
import com.system.management.university.model.Instructor;
import com.system.management.university.model.Lesson;
import com.system.management.university.model.Student;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.repository.InstructorRepository;
import com.system.management.university.repository.LessonRepository;
import com.system.management.university.repository.StudentRepository;
import com.system.management.university.service.abstracts.LessonService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonManager implements LessonService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final ModelMapperService modelMapperService;
    private final InstructorRepository instructorRepository;
    private final DepartmentRepository departmentRepository;

    public LessonManager(LessonRepository lessonRepository, StudentRepository studentRepository, ModelMapperService modelMapperService, InstructorRepository instructorRepository, DepartmentRepository departmentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.modelMapperService = modelMapperService;
        this.instructorRepository = instructorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<LessonDTO> getAll() {
        Optional<List<Lesson>> lessons = Optional.of(lessonRepository.findAll());
        if (!lessons.isPresent()){
            throw new NotFoundExceptions("there is no lesson present");
        }
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        lessons.get().forEach(lesson -> {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setId(lesson.getId());
            lessonDTO.setName(lesson.getName());
            lessonDTO.setCredit(lesson.getCredit());
            lessonDTO.setSemester(lesson.getSemester());
            lessonDTO.setInstructorId(lesson.getInstructor().getId());
            lessonDTO.setDepartmentId(lesson.getDepartment().getId());
            List<Student> students = (List<Student>) studentRepository.findAllByLessonsId(lesson.getId());
            lessonDTO.setStudents(
                    students.stream().map(student ->
                            modelMapperService.forResponse().map(student,StudentDTO.class)).collect(Collectors.toList()));
                    lessonDTOS.add(lessonDTO);
        });
            return lessonDTOS;
    }

    @Override
    public LessonDTO getById(Long id) {

        Optional<Lesson> lesson = lessonRepository.findById(id);
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.get().getId());
        lessonDTO.setName(lesson.get().getName());
        lessonDTO.setCredit(lesson.get().getCredit());
        lessonDTO.setSemester(lesson.get().getSemester());
        lessonDTO.setInstructorId(lesson.get().getInstructor().getId());
        lessonDTO.setDepartmentId(lesson.get().getDepartment().getId());
        List<Student> students = (List<Student>) studentRepository.findAllByLessonsId(lesson.get().getId());
        lessonDTO.setStudents(students.stream().map(
                student -> modelMapperService.forResponse().map(student,StudentDTO.class)).collect(Collectors.toList())
        );
        return lessonDTO;
    }

    @Override
    public void addLesson(LessonDTO lessonDTO) {
        Department department = departmentRepository.findById(lessonDTO.getDepartmentId())
                .orElseThrow(() -> new NotFoundExceptions("Department not found with id: " + lessonDTO.getDepartmentId()));

        Instructor instructor = instructorRepository.findById(lessonDTO.getInstructorId())
                .orElseThrow(() -> new NotFoundExceptions("Instructor not found with id: " + lessonDTO.getInstructorId()));

        Lesson lesson = modelMapperService.forRequest().map(lessonDTO, Lesson.class);

        lesson.setDepartment(department);
        lesson.setInstructor(instructor);

        department.getLessons().add(lesson);
        instructor.getLessons().add(lesson);

        lessonRepository.save(lesson);
    }

    @Override
    public void update(Long id, LessonDTO lessonDTO) {
        Optional<Lesson> lessonOptional  = lessonRepository.findById(id);
        if (!lessonOptional.isPresent()){
            new NotFoundExceptions("Lesson not found with id: " + lessonDTO.getDepartmentId());
        }

        Lesson lesson = lessonOptional.get();
        lesson.setName(lessonDTO.getName());
        lesson.setCredit(lessonDTO.getCredit());
        lesson.setSemester(lesson.getSemester());
        lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {

    }
}
