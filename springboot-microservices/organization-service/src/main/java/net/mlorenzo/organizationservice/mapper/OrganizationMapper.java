package net.mlorenzo.organizationservice.mapper;

import net.mlorenzo.organizationservice.dto.OrganizationDto;
import net.mlorenzo.organizationservice.entity.Organization;
import org.springframework.beans.BeanUtils;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        BeanUtils.copyProperties(organization, organizationDto);
        return organizationDto;
    }

    public static Organization mapToOrganizationEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationDto, organization, "id");
        return organization;
    }
}
