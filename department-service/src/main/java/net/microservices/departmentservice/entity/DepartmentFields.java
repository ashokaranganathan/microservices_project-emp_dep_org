package net.microservices.departmentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class DepartmentFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Department ID")
    private Long id;
    @Column(name = "Department Name")
    private String departmentName;
    @Column(name = "Department Description")
    private String departmentDescription;
    @Column(name = "Department Code")
    private String departmentCode;
}
