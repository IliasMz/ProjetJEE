package meryem.emsi.gestiondemployes.web;

import meryem.emsi.gestiondemployes.entities.Departement;
import meryem.emsi.gestiondemployes.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departements")
public class DepartementRestController {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementRestController(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @GetMapping
    public Page<Departement> getAllDepartements(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "search", defaultValue = "") String searchName) {

        return departementRepository.findByNomContaining(searchName, PageRequest.of(page, size));
    }

    @PostMapping
    public Departement saveDepartement(@RequestBody Departement departement) {
        return departementRepository.save(departement);
    }

    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable int id, @RequestBody Departement departement) {
        departement.setId(id);
        return departementRepository.save(departement);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable int id) {
        departementRepository.deleteById(id);
    }
}

