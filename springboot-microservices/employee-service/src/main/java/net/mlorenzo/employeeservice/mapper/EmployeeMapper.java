package net.mlorenzo.employeeservice.mapper;

import net.mlorenzo.employeeservice.dto.EmployeeDto;
import net.mlorenzo.employeeservice.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto mapToDto(Employee employee);
    Employee maptoEntity(EmployeeDto employeeDto);
}
