package com.refs.commands;


import com.refs.models.Availability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String bubu;

    private String date;

    private String description;

    private String title;

    private String url;

    private  Long category_id;

    private Availability availability;

    private Set<CommentCommand> comments = new HashSet();

    //private Set<CategoryCommand> categories = new HashSet<>();

    private String[] categories;

    private AdvertisementInfoCommand advertisementInfo;

}
