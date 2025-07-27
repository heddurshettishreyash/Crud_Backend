package com.example.demo.controller;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO createdOrganization = organizationService.createOrganization(organizationDTO);
        return ResponseEntity.ok(createdOrganization);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        List<OrganizationDTO> organizations = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable int id) {
        OrganizationDTO organization = organizationService.getOrganizationById(id);
        return organization != null ? ResponseEntity.ok(organization) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable int id, @RequestBody OrganizationDTO updatedOrganizationDTO) {
        OrganizationDTO updatedOrganization = organizationService.updateOrganization(id, updatedOrganizationDTO);
        return updatedOrganization != null ? ResponseEntity.ok(updatedOrganization) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizationById(@PathVariable int id) {
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrganizations() {
        organizationService.deleteAllOrganizations();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<OrganizationDTO>> findByFilterSortAndPage(
            @RequestParam String sortBy,
            @RequestParam String direction,
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        List<OrganizationDTO> organizations = organizationService.findByFilterSortAndPage(sortBy, direction, limit, offset, filterType, filterValue);
        return ResponseEntity.ok(organizations);
    }
}
