package com.dodemy.plantplaces.dao;

import java.util.Set;

import com.dodemy.plantplaces.dto.PlantDTO;


public interface IOfflinePlantDAO extends IPlantDAO {
    void insert(PlantDTO plant);

    int countPlants();

    Set<Integer> fetchAllGuids();
}
