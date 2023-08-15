package com.system.management.university.service.business;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Department;
import com.system.management.university.model.Faculty;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.repository.FacultyRepository;
import com.system.management.university.service.abstracts.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentManager implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapperService modelMapperService;
    private final FacultyRepository facultyRepository;

    public DepartmentManager(DepartmentRepository departmentRepository, ModelMapperService modelMapperService, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.modelMapperService = modelMapperService;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        List<DepartmentDTO> departmentDtos = departments.stream()
                            .map(department -> modelMapperService.forResponse()
                            .map(department,DepartmentDTO.class)).collect(Collectors.toList());
        return departmentDtos;
    }

    @Override
    public DepartmentDTO getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        DepartmentDTO departmentById = modelMapperService.forResponse().map(department,DepartmentDTO.class);
        return departmentById;
    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        Optional<Faculty> faculty = facultyRepository.findById(departmentDTO.getFacultyId());
        Department department = modelMapperService.forRequest().map(departmentDTO,Department.class);
        Set<Department> departments = new HashSet<>();
        departments.add(department);
        if (faculty.isPresent()){
            faculty.get().setDepartments(departments);
            facultyRepository.save(faculty.get());
        }
        departmentRepository.save(department);
    }

    @Override
    public void update(Long id , DepartmentDTO departmentDTO) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setName(departmentDTO.getName());
        }
        else {
            throw new NotFoundExceptions("department not found with id you provide");
        }
    }

    @Override
    public void delete(Long id) {
        if (departmentRepository.findById(id).isPresent()){
            departmentRepository.deleteById(id);
        }
        else {
            throw new NotFoundExceptions("department not found with id you provide");
        }
    }
}
