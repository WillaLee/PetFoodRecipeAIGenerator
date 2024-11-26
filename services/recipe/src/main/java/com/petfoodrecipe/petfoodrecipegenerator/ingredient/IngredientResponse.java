package com.petfoodrecipe.petfoodrecipegenerator.ingredient;

public record IngredientResponse(
        int id,
        String name,
        float energy,
        float protein,
        float fat,
        float calcium,
        float phosphorus,
        float potassium,
        float sodium,
        float magnesium,
        float iron,
        float copper,
        float zinc,
        float manganese,
        float vitaminA,
        float vitaminD,
        float vitaminE,
        float vitaminB1,
        float vitaminB2,
        float vitaminB3,
        float vitaminB5,
        float vitaminB6,
        float choline
) {}

