package net.mlorenzo.departmentservice.controller;

import lombok.RequiredArgsConstructor;
import net.mlorenzo.departmentservice.dto.DepartmentDto;
import net.mlorenzo.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.saveDepartment(departmentDto);
    }

    @GetMapping("/{code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode) {
        return departmentService.getDepartmentByCode(departmentCode);
    }
}
