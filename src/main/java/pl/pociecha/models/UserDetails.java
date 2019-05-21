package pl.pociecha.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "user_details")
public class UserDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(mappedBy = "userDetails")
    private User user;

    private String profession;

    public UserDetails(String profession) {
        this.profession = profession;
    }

    public UserDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
