package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoriteNeighbour = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


       //	e. 1 .Add getFavorites method
    @Override
    public List<Neighbour> getFavoriteNeighbours(){
        favoriteNeighbour.clear();
        for (Neighbour n : neighbours) {
            if(n.isFavorites()){
                favoriteNeighbour.add(n);
            }
        }
        return favoriteNeighbour;
    }
    //12. l'implémentation de la méthode retourn Neighbour par son id

    @Override
    public Neighbour getNeighbourById(int id) {
        return neighbours.get(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void addNeighbour(Neighbour neighbour) { neighbours.add(neighbour);
    }
}



