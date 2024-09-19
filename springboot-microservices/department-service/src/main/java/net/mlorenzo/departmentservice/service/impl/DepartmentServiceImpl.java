package net.mlorenzo.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.mlorenzo.departmentservice.dto.DepartmentDto;
import net.mlorenzo.departmentservice.entity.Department;
import net.mlorenzo.departmentservice.exception.CodeAlreadyExistsException;
import net.mlorenzo.departmentservice.exception.ResourceNotFoundException;
import net.mlorenzo.departmentservice.mapper.DepartmentMappper;
import net.mlorenzo.departmentservice.repository.DepartmentRepository;
import net.mlorenzo.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMappper departmentMappper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        if(departmentRepository.findByCode(departmentDto.getCode()).isPresent())
            throw new CodeAlreadyExistsException("Code already exists for Department");
        Department department = departmentMappper.mapToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return departmentMappper.mapToDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        return departmentRepository.findByCode(code)
                // Versión simplificada de la expresión "department -> departmentMappper.mapToDto(department)"
                .map(departmentMappper::mapToDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Department not found with code: %s", code)));
    }
}
