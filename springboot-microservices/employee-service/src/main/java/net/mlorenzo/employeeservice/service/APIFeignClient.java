package net.mlorenzo.employeeservice.service;

import net.mlorenzo.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080", path = "api/departments", value = "DEPARTMENT-SERVICE")
@FeignClient(path = "api/departments", value = "DEPARTMENT-SERVICE")
public interface APIFeignClient {

    @GetMapping("{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code);
}
