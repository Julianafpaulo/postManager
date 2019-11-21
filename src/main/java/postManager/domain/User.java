package postManager.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuarios")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(name = "Nome")
    private String name;
    @Column(name = "Email")
    private double email;


    public User(Long id, String name, double email) {
         this.id = id;
        this.name = name;
        this.email = email;
    }
    public User(String name, double email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEmail() {
        return email;
    }

    public void setEmail(double email) {
        this.email = email;
    }

}
