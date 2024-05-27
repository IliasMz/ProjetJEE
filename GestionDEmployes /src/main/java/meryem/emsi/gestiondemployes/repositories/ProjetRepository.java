package meryem.emsi.gestiondemployes.repositories;




import meryem.emsi.gestiondemployes.entities.Projet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {
    Page<Projet> findByDescriptionContaining(String description, Pageable pageable);

    Projet findByDescription(String description);
}
