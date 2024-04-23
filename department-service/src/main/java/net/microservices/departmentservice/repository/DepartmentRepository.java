package net.microservices.departmentservice.repository;

import net.microservices.departmentservice.entity.DepartmentFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentFields, Long> {

    DepartmentFields findByDepartmentCode(String departmentCode);

}
