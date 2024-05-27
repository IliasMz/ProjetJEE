package meryem.emsi.gestiondemployes.web;


import meryem.emsi.gestiondemployes.entities.Employee;
import meryem.emsi.gestiondemployes.repositories.DepartementRepository;
import meryem.emsi.gestiondemployes.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/employees")
    public String findEmployees(Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "3") int size,
                                @RequestParam(name = "search", defaultValue = "") String searchName) {
        Page<Employee> pageEmployees = employeeRepository.findByNomContaining(searchName, PageRequest.of(page, size));
        //Page<Employee> pageEmployees = employeeRepository.findAll(PageRequest.of(page,size));

        int[] pages = new int[pageEmployees.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("employees", pageEmployees.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "AllOfEmployes"; // employees.html dans les templates

    }

    @GetMapping("/add")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); // Ajoute un employé vide au modèle

        // Ajoute la liste des départements au modèle
        model.addAttribute("departements", departementRepository.findAll()); // Assurez-vous d'injecter departementRepository

        return "AddEmployes"; // Nom du template Thymeleaf pour le formulaire
    }


    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee); // Sauvegarde de l'employé dans la base de données
        return "redirect:/employees"; // Redirection vers la liste des employés
    }
    @GetMapping("/edit")
    public String showEditEmployeeForm(@RequestParam("id") Long id, Model model) {
        // Récupérer l'employé à modifier par son ID
        Optional<Employee> optionalEmployee = employeeRepository.findById(Math.toIntExact(id));
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            model.addAttribute("employee", employee);
            // Ajouter la liste des départements au modèle
            model.addAttribute("departements", departementRepository.findAll());
            return "EditEmployes"; // Vue du formulaire de modification
        } else {
            return "error"; // Vue d'erreur
        }
    }
    @PostMapping("/update")
    public String updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id,
                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "search", defaultValue = "") String search) {
        // Supprimer l'employé de la base de données en utilisant l'ID
        System.out.println("Delete" + id);
        employeeRepository.deleteById(id);
        return "redirect:/employees?page=" + page + "&search=" + search; // Rediriger vers la liste des employés après la suppression
    }







}
