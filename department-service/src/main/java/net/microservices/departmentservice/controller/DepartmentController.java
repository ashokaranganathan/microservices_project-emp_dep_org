package net.microservices.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.DepartmentFields;
import net.microservices.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("/post")
    public ResponseEntity<DepartmentDto> postController(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<DepartmentDto>> getContoller(){
        return new ResponseEntity<>(departmentService.readDepartment(), HttpStatus.OK);
    }

    @GetMapping("/get/{departmentCode}")
    public ResponseEntity<DepartmentDto> getByDepartmentCodeController(@PathVariable String departmentCode){
        return new ResponseEntity<>(departmentService.readByDepartmentCode(departmentCode),HttpStatus.OK);
    }
}
