package com.petfoodrecipe.petfoodrecipegenerator.recipeingredient;

import com.petfoodrecipe.petfoodrecipegenerator.recipe.Recipe;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;

@Entity(name = "recipe_ingredient_details")
public class RecipeIngredient {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private int ingredientId;
    // Weight in gram
    @Positive
    private int weightInGrams;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonIgnore
    private Recipe recipe;

    public RecipeIngredient(int ingredientId, int weightInGrams, Recipe recipe) {
        this.ingredientId = ingredientId;
        this.weightInGrams = weightInGrams;
        this.recipe = recipe;
    }

    public RecipeIngredient() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", ingredientId=" + ingredientId +
                ", weightInGrams=" + weightInGrams +
                ", recipe=" + recipe +
                '}';
    }
}
