package com.dodemy.plantplaces.dao;

import java.util.List;

import com.dodemy.plantplaces.dto.PlantDTO;
import com.dodemy.plantplaces.dto.SpecimenDTO;


public interface ISpecimenDAO {

    /**
     * Save the SpecimenDTO to the persistence layer.
     */
    public void save(SpecimenDTO specimen) throws Exception;

    /**
     * Return all plants with specimens that match the search term.
     * @param searchTerm
     * @return
     */
    public List<PlantDTO> search(String searchTerm);

    /**
     * Return all specimens near a certain point.
     * @param latitude
     * @param longitude
     * @param range
     * @return
     */
    public List<PlantDTO> search(double latitude, double longitude, double range);
}
