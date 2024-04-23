package net.microservices.organizationservice.service;

import lombok.AllArgsConstructor;
import net.microservices.organizationservice.dto.OrganizationDto;
import net.microservices.organizationservice.entity.Organization;
import net.microservices.organizationservice.mapper.AutoMapper;
import net.microservices.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationDto saveOrganization(OrganizationDto organizationDto){
        Organization save = AutoMapper.MAPPER.mapToOrganization(organizationDto);
        Organization saved = organizationRepository.save(save);
        return AutoMapper.MAPPER.mapToOrganizationDto(saved);
    }

    public List<OrganizationDto> getOrganizations(){
        List<Organization> list = organizationRepository.findAll();
        return list.stream().map(AutoMapper.MAPPER::mapToOrganizationDto).collect(Collectors.toList());
    }

    public OrganizationDto getOrganizationByCode(String organizationCode){
        Organization getByCode = organizationRepository.findByOrganizationCode(organizationCode);
        return AutoMapper.MAPPER.mapToOrganizationDto(getByCode);
    }
}
