package com.openclassrooms.entrevoisins.model;

import java.util.Objects;
import java.util.Random;

import static com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator.NEW_NEIGHBOUR;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;
    //b.	Add a Boolean for the class and constructor
    /** Favorites */
    private boolean favorites;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param favorites
     */
    public Neighbour(Integer id, String name, String avatarUrl,boolean favorites) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        //b.	Add a Boolean for the class and constructor
        this.favorites = favorites;
    }
    //d.	Add Generate Getter and Setter
    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }
    //
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Neighbour addNewNeighbour(){
        return NEW_NEIGHBOUR.get(new Random().nextInt(NEW_NEIGHBOUR.size()));
    }


}
