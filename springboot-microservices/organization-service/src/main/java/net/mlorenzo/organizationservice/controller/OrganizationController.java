package net.mlorenzo.organizationservice.controller;

import lombok.RequiredArgsConstructor;
import net.mlorenzo.organizationservice.dto.OrganizationDto;
import net.mlorenzo.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrganizationDto createOrganization(@RequestBody OrganizationDto organizationDto) {
        return organizationService.saveOrganization(organizationDto);
    }

    @GetMapping("/{code}")
    public OrganizationDto getOrganizationByCode(@PathVariable(name = "code") String organizationCode) {
        return organizationService.getOrganizationByCode(organizationCode);
    }
}
