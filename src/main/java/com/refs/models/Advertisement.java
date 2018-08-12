package com.refs.models;

import lombok.Data;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String url;
    private String date;
    private String bubu;

    @ManyToMany
    @JoinTable(name = "advertisement_category",
            joinColumns = @JoinColumn(name = "advertisement_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advertisement")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private AdvertisementInfo advertisementInfo;

    @Enumerated(value = EnumType.STRING)
    private Availability availability;

    public String getBubu() {
        return bubu;
    }

    public void setBubu(String bubu) {
        this.bubu = bubu;
    }

//private Byte[] image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AdvertisementInfo getAdvertisementInfo() {
        return advertisementInfo;
    }

    public void setAdvertisementInfo(AdvertisementInfo advertisementInfo) {
        this.advertisementInfo = advertisementInfo;
    }

    public Boolean isChecked(Array fullArr, Long advertisementId) {
        return true;
    }

}