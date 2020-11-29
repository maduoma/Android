package com.dodemy.room_sampleformultipleentities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**********************************
 * Creating Entity class
 * Room creates a table for each class annotated with @Entity; the fields in the class correspond to columns in the table. Therefore, the entity classes tend to be small model classes that don’t contain any logic. For ex:
 */
@Entity(tableName = "Accessories")
public class Accessory {

    @PrimaryKey
    private int accessoryId;

    private String accessoryName;

    private int accessoryPoints;

    private int accessoryPurchased;

    public Accessory(int accessoryId, String accessoryName, int accessoryPoints, int accessoryPurchased) {
        this.accessoryId = accessoryId;
        this.accessoryName = accessoryName;
        this.accessoryPoints = accessoryPoints;
        this.accessoryPurchased = accessoryPurchased;
    }
}