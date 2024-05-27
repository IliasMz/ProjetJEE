package meryem.emsi.gestiondemployes.repositories;

import meryem.emsi.gestiondemployes.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);

    Page<Employee> findByNomContaining(String searchName, PageRequest of);
}
