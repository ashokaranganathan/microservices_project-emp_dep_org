package net.microservices.departmentservice.service;

import lombok.AllArgsConstructor;
import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.DepartmentFields;
import net.microservices.departmentservice.mapper.AutoMapper;
import net.microservices.departmentservice.repository.DepartmentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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

    public ByteArrayInputStream getDataDownloaded() throws IOException {
        List<DepartmentFields> departments = departmentRepository.findAll();
        List<DepartmentDto> department = departments.stream().map(AutoMapper.MAPPER::mapToDepartmentDto).collect(Collectors.toList());
        ByteArrayInputStream data = ExcelUtil.dataToExcel(department);
        return data;

    }

    public void updateExcelSheet(){
        String excelSheetPath = "C:\\Users\\2158363\\Downloads\\departments.xlsx";

        try{
            FileInputStream fileInputStream = new FileInputStream(excelSheetPath);

            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int lastRowCount = sheet.getLastRowNum();

            List<DepartmentDto> departmentDtoList = readDepartment();
            for (int i = 0; i < departmentDtoList.size(); i++) {
                Row dataRow = sheet.createRow(++lastRowCount);
                dataRow.createCell(0).setCellValue(departmentDtoList.get(i).getDepartmentName());
                dataRow.createCell(1).setCellValue(departmentDtoList.get(i).getDepartmentDescription());
                dataRow.createCell(2).setCellValue(departmentDtoList.get(i).getDepartmentCode());

            }

            fileInputStream.close();
            FileOutputStream fileOutputStream= new FileOutputStream(excelSheetPath);
            workbook.write(fileOutputStream);
            System.out.println("Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
