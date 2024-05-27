package meryem.emsi.gestiondemployes.repositories;



import meryem.emsi.gestiondemployes.entities.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    Page<Departement> findByNomContaining(String nom, Pageable pageable);
}
