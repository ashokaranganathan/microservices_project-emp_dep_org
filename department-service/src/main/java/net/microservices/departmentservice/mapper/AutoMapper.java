package net.microservices.departmentservice.mapper;

import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.DepartmentFields;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoMapper {

    AutoMapper MAPPER = Mappers.getMapper(AutoMapper.class);

    DepartmentDto mapToDepartmentDto(DepartmentFields departmentFields);

    DepartmentFields mapToDepartment(DepartmentDto departmentDto);
}
