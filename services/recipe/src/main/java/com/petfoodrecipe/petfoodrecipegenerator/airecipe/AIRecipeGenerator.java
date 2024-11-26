package com.petfoodrecipe.petfoodrecipegenerator.airecipe;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientResponse;
import com.petfoodrecipe.petfoodrecipegenerator.pet.Cat;
import com.petfoodrecipe.petfoodrecipegenerator.pet.PetResponse;
import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIRecipeGenerator {
    private String projectId;
    private String location;
    private String modelName;
    private String responseFormat;

    public AIRecipeGenerator() {
        projectId = "pet-food-recipe-generator";
        location = "us-central1";
        modelName = "gemini-1.0-pro-001";
        responseFormat = "id: ingredientId, weight_in_gram: integer";
    }

    public String generateRecipe(List<IngredientResponse> ingredients, PetResponse pet) throws IOException {


        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);

            GenerateContentResponse response = model.generateContent(generatePrompt(ingredients, pet));
            String output = ResponseHandler.getText(response);
            return output;
        }
    }

    private String generatePrompt(List<IngredientResponse> ingredients, PetResponse pet) {
        String petType, lifeStage;
        int requiredEnergy = pet.getMER();
        if (pet instanceof Cat) {
            petType = "cat";
            lifeStage = pet.getLifeStage().equals("GROWTH") ? "kitten" : "adult cat";
        } else {
            petType = "dog";
            lifeStage = pet.getLifeStage().equals("GROWTH") ? "puppy" : "adult dog";
        }
        StringBuilder ingredientList = new StringBuilder();
        for (IngredientResponse ingredient : ingredients) {
            ingredientList.append(ingredient.toString()).append(", ");
        }
        return
                "I have these three ingredients:" + ingredientList.toString() +
                        "My " + petType + " is an " + lifeStage + " requiring " + requiredEnergy + "energy" +
                        "Generate a homemade pet food recipe for them." +
                        "response with only the ingredients in format:" + responseFormat;
    }
}

