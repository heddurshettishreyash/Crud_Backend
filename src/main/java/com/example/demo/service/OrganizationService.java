package com.example.demo.service;

import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Organization;
import com.example.demo.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    private OrganizationDTO convertToDTO(Organization organization) {
        return new OrganizationDTO(
                organization.getOrgId(),
                organization.getOrgName(),
                organization.getOrgAddress(),
                organization.getOrgEmail(),
                organization.getOrgNumber()
        );
    }

    private Organization convertToEntity(OrganizationDTO dto) {
        Organization organization = new Organization();
        organization.setOrgId(dto.getOrgId());
        organization.setOrgName(dto.getOrgName());
        organization.setOrgAddress(dto.getOrgAddress());
        organization.setOrgEmail(dto.getOrgEmail());
        organization.setOrgNumber(dto.getOrgNumber());
        return organization;
    }

    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = convertToEntity(organizationDTO);
        Organization savedOrganization = organizationRepository.save(organization);
        return convertToDTO(savedOrganization);
    }

    public List<OrganizationDTO> findByFilterSortAndPage(String sortBy, String direction, int limit,
                                                         int offset, String filterType, String filterValue) {
        List<Organization> organizations = organizationRepository.findByFilterSortAndPage(sortBy, direction,
                limit, offset, filterType, filterValue);
        return organizations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = (List<Organization>) organizationRepository.findAll();
        return organizations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrganizationDTO getOrganizationById(int id) {
        Organization organization = organizationRepository.findById(id).orElse(null);
        return organization != null ? convertToDTO(organization) : null;
    }

    public OrganizationDTO updateOrganization(int id, OrganizationDTO updatedOrganizationDTO) {
        if (organizationRepository.existsById(id)) {
            Organization organization = convertToEntity(updatedOrganizationDTO);
            organization.setOrgId(id);
            Organization savedOrganization = organizationRepository.save(organization);
            return convertToDTO(savedOrganization);
        }
        return null;
    }

    public void deleteOrganizationById(int id) {
        organizationRepository.deleteById(id);
    }

    public void deleteAllOrganizations() {
        organizationRepository.deleteAll();
    }
}
