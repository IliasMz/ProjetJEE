package meryem.emsi.gestiondemployes.web;


import meryem.emsi.gestiondemployes.entities.Projet;
import meryem.emsi.gestiondemployes.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import java.time.ZoneId;
import java.util.Optional;


@Controller
public class ProjetController {

    @Autowired
    private ProjetRepository projetRepository;

    @GetMapping("/projets")
    public String listerProjets(Model model,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "3") int size,
                                @RequestParam(name="search", defaultValue = "") String searchName){

        Page<Projet> pageProjets = projetRepository.findByDescriptionContaining(searchName, PageRequest.of(page, size));

        int[] pages = new int[pageProjets.getTotalPages()];
        for(int i = 0; i < pages.length; i++)
            pages[i] = i;

        model.addAttribute("projets", pageProjets.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", searchName);
        return "projets"; // projets.html dans les templates
    }

    @GetMapping("/ajouterProjet")
    public String ajouterProjetForm(Model model) {
        model.addAttribute("projet", new Projet());
        return "AddProjet"; // ajouterProjet.html dans les templates
    }

    @PostMapping("/enregistrerProjet")
    public String enregistrerProjet(@ModelAttribute Projet projet) {
        projetRepository.save(projet);
        return "redirect:/projets";
    }
    @GetMapping("/editprojet")
    public String editProjet(Model model,
                             @RequestParam(name = "page") int page,
                             @RequestParam(name = "size") int size,
                             @RequestParam(name = "search") String search,
                             @RequestParam(name = "id") Integer id) {
        Projet projet = projetRepository.findById(id).orElse(null);
        if (projet == null) throw new RuntimeException("Projet non trouvé avec l'ID : " + id);
        model.addAttribute("projet", projet);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchName", search);
        return "EditProjet";
    }

    @GetMapping("/delete")
    public String supprimerProjet(@RequestParam(name = "page") int page,
                                  @RequestParam(name = "size") int size,
                                  @RequestParam(name = "search") String search,
                                  @RequestParam(name = "id") Integer id) {
        // Supprimer le projet avec l'ID spécifié
        projetRepository.deleteById(id);

        // Redirection vers la page des projets avec les paramètres de pagination et de recherche
        return "redirect:/projets?page=" + page + "&size=" + size + "&search=" + search;
    }


}










