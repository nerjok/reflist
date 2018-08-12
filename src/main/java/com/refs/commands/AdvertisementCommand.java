package com.refs.commands;


import com.refs.models.Availability;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 255)
    private String bubu;
    @Size(min = 3, max = 255)
    private String date;
    @Size(min = 3, max = 255)
    private String description;
    @Size(min = 3, max = 255)
    private String title;
    @Size(min = 3, max = 255)
    private String url;

    private  Long category_id;

    private Availability availability;

    private Set<CommentCommand> comments = new HashSet();

    //private Set<CategoryCommand> categories = new HashSet<>();

    public Long[] categories;

    private AdvertisementInfoCommand advertisementInfo;

    public Boolean isChecked(Long[] fullArr, Long advertisementId) {

        return Arrays.asList(fullArr).contains(advertisementId);
        //return true;
    }
}
