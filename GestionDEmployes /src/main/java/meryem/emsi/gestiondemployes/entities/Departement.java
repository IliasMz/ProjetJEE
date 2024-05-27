package meryem.emsi.gestiondemployes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Long Budget;
    @OneToMany
    Collection<Employee> employees;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}