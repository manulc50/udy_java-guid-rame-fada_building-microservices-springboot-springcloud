package net.mlorenzo.employeeservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class APIResponseDto {

    private final EmployeeDto employee;
    private final DepartmentDto department;
    private final OrganizationDto organization;
}
