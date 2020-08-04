package com.dodemy.plantplaces.dao;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import com.dodemy.plantplaces.dto.PlantDTO;


public interface IPlantDAO {
    List<PlantDTO> fetchPlants(String searchTerm) throws IOException, JSONException;
}
