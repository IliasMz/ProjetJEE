package meryem.emsi.gestiondemployes.repositories;



import meryem.emsi.gestiondemployes.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositories  extends JpaRepository<Student, Integer> {


}
