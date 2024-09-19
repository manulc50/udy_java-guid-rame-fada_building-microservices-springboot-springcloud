package net.mlorenzo.employeeservice.service;

import net.mlorenzo.employeeservice.dto.APIResponseDto;
import net.mlorenzo.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
