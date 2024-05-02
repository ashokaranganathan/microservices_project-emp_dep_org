package net.microservices.employeeservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.microservices.employeeservice.dto.APIResponseDto;
import net.microservices.employeeservice.dto.DepartmentDto;
import net.microservices.employeeservice.dto.EmployeeDto;
import net.microservices.employeeservice.dto.OrganizationDto;
import net.microservices.employeeservice.entity.Employee;
import net.microservices.employeeservice.mapper.AutoMapper;
import net.microservices.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebClient webClient;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee employee = AutoMapper.MAPPER.mapToEmployee(employeeDto);
        Employee saved = employeeRepository.save(employee);
        EmployeeDto created = AutoMapper.MAPPER.mapToEmployeeDto(saved);
        return created;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultResponse")

    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultResponse")
    public APIResponseDto getByEmployeeId(Long id){
        Employee employeeById = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/department/get/" + employeeById.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        OrganizationDto organizationDto = webClient.get().uri("http://localhost:8082/api/organization/get/" + employeeById.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = AutoMapper.MAPPER.mapToEmployeeDto(employeeById);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultResponse(Long id, Exception exception){
        Employee employeeById = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Default Department");
        departmentDto.setDepartmentDescription("Default Description");
        departmentDto.setDepartmentCode("Default Code");

        OrganizationDto organizationDto = webClient.get().uri("http://localhost:8082/api/organization/get/" + employeeById.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = AutoMapper.MAPPER.mapToEmployeeDto(employeeById);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<Employee> departments = employeeRepository.findAll();
        List<EmployeeDto> employee = departments.stream().map(AutoMapper.MAPPER::mapToEmployeeDto).collect(Collectors.toList());
        ByteArrayInputStream data = ExcelUtil.dataToExcel(employee);
        return data;

    }
}
