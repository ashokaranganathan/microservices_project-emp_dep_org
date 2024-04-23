package net.microservices.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee ID")
    private Long id;
    @Column(name = "Employee First_Name")
    private String firstName;
    @Column(name = "Employee Last_Name")
    private String lastName;
    @Column(name = "Employee Email")
    private String email;
    @Column(name = "Employee Location")
    private String location;
    private String departmentCode;
    private String organizationCode;
}
