package meryem.emsi.gestiondemployes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String matricule;

    @ManyToOne
    private Departement departement;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "employe")
    private List<Projet> projets;

    public Employee(Object o, String amine, String number, Departement retrievedDepartement1) {
    }

    public void setProjet(List<Projet> list) {
    }

    public void setRole(String emp) {
    }


    // Getters, setters et constructeurs supplémentaires
}
