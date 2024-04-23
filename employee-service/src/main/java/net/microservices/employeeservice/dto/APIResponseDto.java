package net.microservices.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {

    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganizationDto organization;
}
