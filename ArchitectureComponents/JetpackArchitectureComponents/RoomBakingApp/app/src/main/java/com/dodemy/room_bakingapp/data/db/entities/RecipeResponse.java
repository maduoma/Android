
package com.dodemy.room_bakingapp.data.db.entities;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipe")
public class RecipeResponse implements Parcelable {

    @PrimaryKey
    @Expose
    public int id;
    @Expose
    private String name;
    @Ignore
    @Embedded
    @Expose
    private List<Ingredient> ingredients = null;
    @Ignore
    @Embedded
    @Expose
    private List<Step> steps = null;
    @Expose
    private int servings;
    @Expose
    private String image;

    public RecipeResponse(int id, String name, List<Ingredient> ingredients, List<Step> steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public static Creator<RecipeResponse> CREATOR = new Creator<RecipeResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RecipeResponse createFromParcel(Parcel in) {
            return new RecipeResponse(in);
        }

        public RecipeResponse[] newArray(int size) {
            return (new RecipeResponse[size]);
        }

    };

//    public RecipeResponse(Parcel in) {
//        this.id = in.readInt();
//        this.name = in.readString();
//        this.ingredients = new ArrayList<Ingredient>();
//        in.readTypedList(this.ingredients, Ingredient.CREATOR);
//        this.steps = new ArrayList<Step>();
//        in.readTypedList(this.steps, Step.CREATOR);
//        this.servings = in.readInt();
//        this.image = in.readString();
//    }

    private RecipeResponse(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        ingredients = new ArrayList<Ingredient>();
        steps = new ArrayList<Step>();
        in.readList(this.ingredients, (Ingredient.class.getClassLoader()));
        in.readList(this.steps, (Step.class.getClassLoader()));
        this.servings = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public RecipeResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int describeContents() {
        return 0;
    }

}
