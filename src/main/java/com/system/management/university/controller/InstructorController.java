package com.system.management.university.controller;

import com.system.management.university.dtos.InstructorDTO;
import com.system.management.university.service.abstracts.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(instructorService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable("id") Long id){
        return ResponseEntity.ok(instructorService.getById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody InstructorDTO instructorDTO){
        instructorService.add(instructorDTO);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody InstructorDTO instructorDTO){
        instructorService.update(id,instructorDTO);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        instructorService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
