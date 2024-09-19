package net.mlorenzo.organizationservice.service.impl;

import lombok.RequiredArgsConstructor;
import net.mlorenzo.organizationservice.dto.OrganizationDto;
import net.mlorenzo.organizationservice.entity.Organization;
import net.mlorenzo.organizationservice.mapper.OrganizationMapper;
import net.mlorenzo.organizationservice.repository.OrganizationRepository;
import net.mlorenzo.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganizationEntity(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String code) {
        Organization organization = organizationRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Organization with id %s not found", code)));
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
