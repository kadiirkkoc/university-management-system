package com.system.management.university.service.business;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.dtos.FacultyDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.exceptions.ResourceAlreadyExistsException;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Department;
import com.system.management.university.model.Faculty;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.repository.FacultyRepository;
import com.system.management.university.service.abstracts.FacultyService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyManager implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapperService modelMapperService;
    private final DepartmentRepository departmentRepository;

    public FacultyManager(FacultyRepository facultyRepository, ModelMapperService modelMapperService, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.modelMapperService = modelMapperService;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<FacultyDTO> getAll() {
        Optional<List<Faculty>> faculties = Optional.of(facultyRepository.findAll());
        if (!faculties.isPresent()){
            throw new NotFoundExceptions("there is no faculty present");
        }
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        faculties.get().forEach(f-> {
            FacultyDTO  dto = new FacultyDTO();
            dto.setId(f.getId());
            dto.setCampus(f.getCampus());
            dto.setFacultyName(f.getName());
            Set<Department> departments = departmentRepository.findAllByFacultyId(f.getId());
            dto.setDepartments(
                    departments.stream().map(department -> modelMapperService.forResponse()
                            .map(department,DepartmentDTO.class)).collect(Collectors.toSet())
            );
            facultyDTOS.add(dto);
        });
        return facultyDTOS;
    }

    @Override
    public FacultyDTO getById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);

        if (!faculty.isPresent()){
            throw new NotFoundExceptions("there is no faculty present with id you provide");
        }
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.get().getId());
        facultyDTO.setFacultyName(faculty.get().getName());
        facultyDTO.setCampus(faculty.get().getCampus());
        Set<Department> departments = departmentRepository.findAllByFacultyId(faculty.get().getId());
        facultyDTO.setDepartments(
                departments.stream().map(department -> modelMapperService.forResponse().map(department,DepartmentDTO.class)).collect(Collectors.toSet())
        );
        return facultyDTO;
    }

    @Override
    public void addFaculty(FacultyDTO facultyDTO) {
        List<Faculty> faculties = facultyRepository.findAll();
        for (Faculty faculty : faculties){
            if ((faculty.getName()).equals(facultyDTO.getFacultyName())){
                throw new ResourceAlreadyExistsException("already there is an faculty with this name");
            }
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getFacultyName());
        faculty.setCampus(facultyDTO.getCampus());
        facultyRepository.save(faculty);
    }

    @Override
    public void updateFaculty(Long id,FacultyDTO facultyDTO) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (!facultyOptional.isPresent()) {
            throw new NotFoundExceptions("faculty not found with id you provide");
        }
        Faculty faculty = facultyOptional.get();
        faculty.setName(facultyDTO.getFacultyName());
        faculty.setCampus(facultyDTO.getCampus());
        facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        if (facultyRepository.findById(id).isPresent()){
            facultyRepository.deleteById(id);
        }
        else {
            throw new NotFoundExceptions("faculty not found with id you provide");
        }
    }
}
