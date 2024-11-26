package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;

import java.util.List;

public class RecipeRequest {

    private List<RecipeIngredient> ingredient;

    public RecipeRequest() {
    }

    public List<RecipeIngredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<RecipeIngredient> ingredient) {
        this.ingredient = ingredient;
    }
}
