package net.mlorenzo.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.mlorenzo.employeeservice.dto.APIResponseDto;
import net.mlorenzo.employeeservice.dto.EmployeeDto;
import net.mlorenzo.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.created(null).body(employeeService.saveEmployee(employeeDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
}
