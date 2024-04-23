package net.microservices.departmentservice.service;

import lombok.AllArgsConstructor;
import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.DepartmentFields;
import net.microservices.departmentservice.mapper.AutoMapper;
import net.microservices.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDto saveDepartment(DepartmentDto departmentDto){
        DepartmentFields departmentFields = AutoMapper.MAPPER.mapToDepartment(departmentDto);
        DepartmentFields saved = departmentRepository.save(departmentFields);
        DepartmentDto created = AutoMapper.MAPPER.mapToDepartmentDto(saved);
        return created;
    }

    public List<DepartmentDto> readDepartment(){
        List<DepartmentFields> list = departmentRepository.findAll();
        return list.stream().map(AutoMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
    }

    public DepartmentDto readByDepartmentCode(String departmentCode){
        DepartmentFields getByCode = departmentRepository.findByDepartmentCode(departmentCode);
        return AutoMapper.MAPPER.mapToDepartmentDto(getByCode);
    }
}
