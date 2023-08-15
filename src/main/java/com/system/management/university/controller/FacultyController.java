package com.system.management.university.controller;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.dtos.FacultyDTO;
import com.system.management.university.service.abstracts.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable("id") Long id){
        return ResponseEntity.ok(facultyService.getById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createFaculty(@RequestBody FacultyDTO facultyDTO){
        facultyService.addFaculty(facultyDTO);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFaculty(@PathVariable("id") Long id,@RequestBody FacultyDTO facultyDTO){
        facultyService.updateFaculty(id,facultyDTO);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable("id") Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok("deleted");
    }
}
