package com.example.demo.service;
import com.example.demo.dto.ApplicationDTO;
import com.example.demo.dto.OrganizationDTO;
import com.example.demo.model.Application;
import com.example.demo.model.Organization;
import com.example.demo.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private OrganizationService organizationService;

    // Convert ApplicationDTO to Application
    private Application convertToEntity(ApplicationDTO dto) {
        Application application = new Application();
        application.setAppId(dto.getAppId());
        application.setAppName(dto.getAppName());
        application.setAppDesc(dto.getAppDesc());
        application.setAppType(dto.getAppType());

        OrganizationDTO organizationDTO = organizationService.getOrganizationById(dto.getOrgId());
        Organization organization = new Organization();
        organization.setOrgId(organizationDTO.getOrgId());

        application.setOrganization(organization);

        return application;
    }

    private ApplicationDTO convertToDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setAppId(application.getAppId());
        dto.setAppName(application.getAppName());
        dto.setAppDesc(application.getAppDesc());
        dto.setAppType(application.getAppType());
        dto.setOrgId(application.getOrganization().getOrgId());
        return dto;
    }

    public ApplicationDTO createApplication(ApplicationDTO applicationDTO) {
        Application application = convertToEntity(applicationDTO);
        Application savedApplication = applicationRepository.save(application);
        return convertToDTO(savedApplication);
    }

    public List<ApplicationDTO> findByFilterSortAndPage(String sortBy, String direction,
                                                        int limit, int offset, String filterType, String filterValue) {
        List<Application> applications = applicationRepository.findByFilterSortAndPage(sortBy, direction, limit, offset, filterType, filterValue);
        return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<ApplicationDTO> getAllApplications() {
        List<Application> applications = (List<Application>) applicationRepository.findAll();
        return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ApplicationDTO getApplicationById(int id) {
        Application application = applicationRepository.findById(id).orElse(null);
        return application != null ? convertToDTO(application) : null;
    }

    public ApplicationDTO updateApplication(int id, ApplicationDTO updatedApplicationDTO) {
        if (applicationRepository.existsById(id)) {
            Application application = convertToEntity(updatedApplicationDTO);
            application.setAppId(id);
            Application savedApplication = applicationRepository.save(application);
            return convertToDTO(savedApplication);
        }
        return null;
    }

    public void deleteApplicationById(int id) {
        applicationRepository.deleteById(id);
    }

    public void deleteAllApplications() {
        applicationRepository.deleteAll();
    }
}