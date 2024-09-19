package net.mlorenzo.employeeservice.service.impl;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import net.mlorenzo.employeeservice.dto.APIResponseDto;
import net.mlorenzo.employeeservice.dto.DepartmentDto;
import net.mlorenzo.employeeservice.dto.EmployeeDto;
import net.mlorenzo.employeeservice.dto.OrganizationDto;
import net.mlorenzo.employeeservice.entity.Employee;
import net.mlorenzo.employeeservice.exception.EmailAlreadyExistsException;
import net.mlorenzo.employeeservice.exception.ResourceNotFoundException;
import net.mlorenzo.employeeservice.mapper.EmployeeMapper;
import net.mlorenzo.employeeservice.repository.EmployeeRepository;
import net.mlorenzo.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    //private final RestTemplate restTemplate;
    private final WebClient webClient;
    //private final APIFeignClient apiFeignClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                               WebClient.Builder webClientBuilder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.webClient = webClientBuilder.build();
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if(employeeRepository.findByEmail(employeeDto.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists for Employee");
        Employee employee = employeeMapper.maptoEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToDto(savedEmployee);
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long id) {
        log.info("inside getEmployeeById method");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee not found with id: %d", id)));
        final String departmentUri = "http://department-service/api/departments/" + employee.getDepartmentCode();
        /*ResponseEntity<DepartmentDto> response = restTemplate.getForEntity(departmentUri, DepartmentDto.class);
        DepartmentDto departmentDto = response.getBody();*/
        DepartmentDto departmentDto = webClient.get()
                .uri(departmentUri)
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        final String organizationUri = "http://organization-service/api/organizations/" + employee.getOrganizationCode();
        OrganizationDto organizationDto = webClient.get()
                .uri(organizationUri)
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();
        //DepartmentDto departmentDto = apiFeignClient.getDepartmentByCode(employee.getDepartmentCode());
        return new APIResponseDto(employeeMapper.mapToDto(employee), departmentDto, organizationDto);
    }

    // Este método puede ser público o privado
    private APIResponseDto getDefaultDepartment(Long id, Exception ex) {
        log.info("inside getDefaultDepartment method");
        EmployeeDto employeeDto = employeeRepository.findById(id)
                // Versión simplificada de la expresión "employee -> employeeMapper.mapToDto(employee)"
                .map(employeeMapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee not found with id: %d", id)));

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("R&D Department");
        departmentDto.setCode("RD001");
        departmentDto.setDescription("Research and Development Department");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("R&D Organization");
        organizationDto.setCode("RD001");
        organizationDto.setDescription("Research and Development Organization");

        return new APIResponseDto(employeeDto, departmentDto, organizationDto);
    }
}
