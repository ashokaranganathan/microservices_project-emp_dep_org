package net.microservices.organizationservice.controller;

import lombok.AllArgsConstructor;
import net.microservices.organizationservice.dto.OrganizationDto;
import net.microservices.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/post")
    public ResponseEntity<OrganizationDto> postController(@RequestBody OrganizationDto organizationDto){
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/list")
    public ResponseEntity<List<OrganizationDto>> getController(){
        return new ResponseEntity<>(organizationService.getOrganizations(),HttpStatus.OK);
    }

    @GetMapping("/get/{organizationCode}")
    public ResponseEntity<OrganizationDto> getByOrganizationCodeController(@PathVariable String organizationCode){
        return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode),HttpStatus.OK);
    }
}
