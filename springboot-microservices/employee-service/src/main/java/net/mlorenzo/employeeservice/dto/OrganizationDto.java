package net.mlorenzo.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationDto {


    private Long id;
    private String name;
    private String description;
    private String code;
}
