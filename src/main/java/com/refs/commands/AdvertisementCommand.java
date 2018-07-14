package com.refs.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementCommand {

    private Long id;

    private String bubu;

    private String date;

    private String description;

    private String title;

    private String url;

    private  Long category_id;
}
