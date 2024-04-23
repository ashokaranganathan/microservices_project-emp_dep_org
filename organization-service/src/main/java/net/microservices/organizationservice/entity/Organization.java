package net.microservices.organizationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Organization ID")
    private Long organizationId;
    @Column(name = "Organization Name")
    private String organizationName;
    @Column(name = "Organization Code")
    private String organizationCode;

}
