package com.refs.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    private Long parentUser;

    private String username;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Advertisement> advertisement = new HashSet<>();

    public  String getRole() {
        return userRole.toString();
    }

}
