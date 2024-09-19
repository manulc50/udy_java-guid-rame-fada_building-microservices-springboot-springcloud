package net.mlorenzo.employeeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private String code;
}
