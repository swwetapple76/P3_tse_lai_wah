package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
    //e.2	Add getFavorites method, for interface
    List<Neighbour> getFavoriteNeighbours();

    //11. une méthode qui retourn Neighbour par son id
    Neighbour getNeighbourById(int id);

    void addNeighbour(Neighbour neighbour);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);


}
