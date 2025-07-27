package com.example.demo.controller;

import com.example.demo.dto.ApplicationDTO;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationDTO> createApplication(@RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO createdApplication = applicationService.createApplication(applicationDTO);
        return ResponseEntity.ok(createdApplication);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        List<ApplicationDTO> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable int id) {
        ApplicationDTO application = applicationService.getApplicationById(id);
        return application != null ? ResponseEntity.ok(application) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApplicationDTO> updateApplication(@PathVariable int id, @RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO updatedApplication = applicationService.updateApplication(id, applicationDTO);
        return updatedApplication != null ? ResponseEntity.ok(updatedApplication) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplicationById(@PathVariable int id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllApplications() {
        applicationService.deleteAllApplications();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ApplicationDTO>> findByFilterSortAndPage(
            @RequestParam String sortBy,
            @RequestParam String direction,
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        List<ApplicationDTO> applications = applicationService.findByFilterSortAndPage(sortBy, direction, limit, offset, filterType, filterValue);
        return ResponseEntity.ok(applications);
    }
}
