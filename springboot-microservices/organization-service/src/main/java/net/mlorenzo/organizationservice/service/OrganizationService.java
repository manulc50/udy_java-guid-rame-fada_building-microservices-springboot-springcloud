package net.mlorenzo.organizationservice.service;

import net.mlorenzo.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String code);
}
