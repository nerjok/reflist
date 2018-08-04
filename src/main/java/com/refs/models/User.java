package com.refs.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Advertisement> advertisement = new HashSet<>();
}
