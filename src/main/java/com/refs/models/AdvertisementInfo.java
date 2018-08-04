package com.refs.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
//@EqualsAndHashCode(exclude = {"advertisement"})
public class AdvertisementInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String advertisementInfo;

    @OneToOne
    private Advertisement advertisement;

}
