package meryem.emsi.gestiondemployes;

import meryem.emsi.gestiondemployes.repositories.DepartementRepository;
import meryem.emsi.gestiondemployes.repositories.EmployeeRepository;
import meryem.emsi.gestiondemployes.repositories.ProjetRepository;
import meryem.emsi.gestiondemployes.repositories.StudentRepositories;
import meryem.emsi.gestiondemployes.entities.Student;
import meryem.emsi.gestiondemployes.entities.Departement;
import meryem.emsi.gestiondemployes.entities.Employee;
import meryem.emsi.gestiondemployes.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class GestionDEmployesApplication implements CommandLineRunner {

	@Autowired
	private StudentRepositories studentRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private ProjetRepository projetRepo;

	@Autowired
	private DepartementRepository departementRepo;


	public static void main(String[] args) {
		SpringApplication.run(GestionDEmployesApplication.class, args);

	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		studentRepo.save(new Student(null, "Meryem96009419", "amine", new Date(), true, null));


		// Enregistrement des départements

		Departement departement1 = new Departement();
		departement1.setNom("Support technique");
		departement1.setBudget(540000000L);
		departementRepo.save(departement1);

		Departement departement2 = new Departement();
		departement2.setNom("Informatique");
		departement2.setBudget(4006700L);
		departementRepo.save(departement2);

		Departement departement3 = new Departement();
		departement3.setNom("Maintenance");
		departement3.setBudget(600000L);
		departementRepo.save(departement3);

		Departement departement4 = new Departement();
		departement4.setNom("DevOps");
		departement4.setBudget(10000000L);
		departementRepo.save(departement4);

		// Récupération des départements depuis la base de données
		Departement retrievedDepartement1 = departementRepo.findById(1).orElse(null);
		Departement retrievedDepartement2 = departementRepo.findById(2).orElse(null);
		Departement retrievedDepartement3 = departementRepo.findById(3).orElse(null);
		Departement retrievedDepartement4 = departementRepo.findById(4).orElse(null);
		// Création des employés et association avec les départements récupérés
		Employee emp10 = new Employee();
		emp10.setNom("Amina");
		emp10.setMatricule("129");
		emp10.setDepartement(departement1);
		employeeRepo.save(emp10);
		Employee emp2 = new Employee();
		emp2.setNom("Youssef");
		emp2.setMatricule("128");
		emp2.setDepartement(departement4);
		employeeRepo.save(emp2);
		Employee emp3 = new Employee();
		emp3.setNom("Hajar");
		emp3.setMatricule("126");
		emp3.setDepartement(departement4);
		employeeRepo.save(emp3);
		Employee emp4 = new Employee();
		emp4.setNom("John");
		emp4.setMatricule("123");
		emp4.setDepartement(departement3);
		employeeRepo.save(emp4);
		Employee emp5 = new Employee();
		emp5.setNom("Meryem");
		emp5.setMatricule("125");
		emp5.setDepartement(departement2);
		employeeRepo.save(emp5);


		// Création des projets
		Projet projet1 = new Projet();
		projet1.setDescription("Développement d'une application web");
		projet1.setDebutProj(new Date());
		projet1.setFinProj(new Date());

		Projet projet2 = new Projet();
		projet2.setDescription("Projet de business intelligence");
		projet2.setDebutProj(new Date());
		projet2.setFinProj(new Date());

		Projet projet3 = new Projet();
		projet3.setDescription("Développement d'une application de gestion de projet");
		projet3.setDebutProj(new Date());
		projet3.setFinProj(new Date());

		Projet projet4 = new Projet();
		projet4.setDescription("Développement d'un site e-commerce");
		projet4.setDebutProj(new Date());
		projet4.setFinProj(new Date());

		Projet projet5 = new Projet();
		projet5.setDescription("Test de performance ");
		projet5.setDebutProj(new Date());
		projet5.setFinProj(new Date());

		projetRepo.saveAll(Arrays.asList(projet1, projet2,projet3,projet4,projet5));

		// Association des projets aux employés
		emp3.setProjet(Arrays.asList(projet1));
		emp2.setProjet(Arrays.asList(projet2));

		employeeRepo.saveAll(Arrays.asList(emp3, emp2));
	}



}
