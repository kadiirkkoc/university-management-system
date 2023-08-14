package com.system.management.university.service.business;

import com.system.management.university.dtos.StudentDto;
import com.system.management.university.model.Department;
import com.system.management.university.model.Student;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.service.abstracts.DepartmentService;
import com.system.management.university.service.abstracts.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentManager implements StudentService {
    private final DepartmentService deparmentService;
    private final DepartmentRepository departmentRepository;

    public StudentManager(DepartmentService deparmentService, DepartmentRepository departmentRepository) {
        this.deparmentService = deparmentService;
        this.departmentRepository = departmentRepository;
    }

    public void add(StudentDto studentDto){
        List<Student> students = new ArrayList<>();
        Optional<Department> department = departmentRepository.findById(1L);
        Student student = new Student();
        student.setDepartment(department.get());
        if(department!=null){
            Department databaseDep = department.get();
            databaseDep.setStudents(students);
            departmentRepository.save(databaseDep);
        }

    }
}
