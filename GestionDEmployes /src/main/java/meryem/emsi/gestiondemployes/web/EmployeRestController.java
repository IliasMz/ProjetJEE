package meryem.emsi.gestiondemployes.web;



import meryem.emsi.gestiondemployes.entities.Employee;
import meryem.emsi.gestiondemployes.repositories.DepartementRepository;
import meryem.emsi.gestiondemployes.repositories.EmployeeRepository;
import meryem.emsi.gestiondemployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeRestController {

    private final EmployeeRepository employeRepository;
    private final DepartementRepository departementRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public EmployeRestController(EmployeeRepository employeRepository, DepartementRepository departementRepository, ProjetRepository projetRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployes() {

        return employeRepository.findAll();
    }

    @PostMapping
    public Employee saveEmploye(@RequestBody Employee employe) {
        return employeRepository.save(employe);
    }

    @PutMapping("/{id}")
    public Employee updateEmploye(@PathVariable Integer id, @RequestBody Employee employe) {
        employe.setId(id);
        return employeRepository.save(employe);
    }

    @DeleteMapping("/{id}")
    public void deleteEmploye(@PathVariable Integer id) {
        employeRepository.deleteById(id);
    }
}

