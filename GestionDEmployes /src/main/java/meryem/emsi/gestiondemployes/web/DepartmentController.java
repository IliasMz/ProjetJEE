package meryem.emsi.gestiondemployes.web;



import meryem.emsi.gestiondemployes.entities.Departement;
import meryem.emsi.gestiondemployes.repositories.DepartementRepository;
import meryem.emsi.gestiondemployes.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/departements")
    public String listDepartements(Model model,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "3") int size,
                                   @RequestParam(name = "search", defaultValue = "") String searchName) {

        Page<Departement> pageDepartements = departementRepository.findByNomContaining(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageDepartements.getTotalPages()];
        for (int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("departements", pageDepartements.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);

        return "departements"; // departements.html dans les templates
    }

    @GetMapping("/addDepartement")
    public String addDepartementForm(Model model) {
        model.addAttribute("departement", new Departement());
        return "AddDepartement";
    }

    @PostMapping("/departements/save")
    public String saveDepartement(@ModelAttribute Departement departement) {
        departementRepository.save(departement);
        return "redirect:/departements";
    }
    @GetMapping("/editdepart")
    public String editDepartement(Model model,
                                  @RequestParam(name = "page") int page,
                                  @RequestParam(name = "size") int size,
                                  @RequestParam(name = "search") String search,
                                  @RequestParam(name = "id") Integer id) {
        Departement departement = departementRepository.findById(id).orElse(null);
        if (departement == null) throw new RuntimeException("Département non trouvé avec l'ID : " + id);
        model.addAttribute("departement", departement);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        return "EditDepartement";
    }





}
