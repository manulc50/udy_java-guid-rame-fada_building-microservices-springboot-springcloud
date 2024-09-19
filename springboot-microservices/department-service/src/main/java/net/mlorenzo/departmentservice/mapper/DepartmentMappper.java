package net.mlorenzo.departmentservice.mapper;

import net.mlorenzo.departmentservice.dto.DepartmentDto;
import net.mlorenzo.departmentservice.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMappper {

    DepartmentDto mapToDto(Department department);
    Department mapToEntity(DepartmentDto departmentDto);
}
