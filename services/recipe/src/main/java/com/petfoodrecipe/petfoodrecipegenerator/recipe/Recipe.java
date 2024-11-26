package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "recipe_details")
public class Recipe {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> ingredients;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Nutrition nutrition;

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", userId=" + userId +
                ", ingredients=" + ingredients +
                ", nutrition=" + nutrition +
                '}';
    }
}
