package com.petfoodrecipe.petfoodrecipegenerator.airecipe;

import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientClient;
import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientNotFoundException;
import com.petfoodrecipe.petfoodrecipegenerator.pet.PetClient;
import com.petfoodrecipe.petfoodrecipegenerator.pet.PetNotFoundException;
import com.petfoodrecipe.petfoodrecipegenerator.pet.PetResponse;
import com.petfoodrecipe.petfoodrecipegenerator.recipe.Recipe;
import com.petfoodrecipe.petfoodrecipegenerator.recipe.RecipeRepository;
import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserClient;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIRecipeService {
    private static final Logger log = LoggerFactory.getLogger(AIRecipeService.class);
    private UserClient userClient;
    private PetClient petClient;
    private AIRecipeGenerator aiRecipeGenerator;
    private IngredientClient ingredientClient;
    private RecipeRepository recipeRepository;

    public AIRecipeService(UserClient userClient, PetClient petClient, AIRecipeGenerator aiRecipeGenerator, IngredientClient ingredientClient, RecipeRepository recipeRepository) {
        this.userClient = userClient;
        this.petClient = petClient;
        this.aiRecipeGenerator = aiRecipeGenerator;
        this.ingredientClient = ingredientClient;
        this.recipeRepository = recipeRepository;
    }

    private void ingredientIsExist(int ingredientId) throws IngredientNotFoundException {
        try {
            ingredientClient.getIngredientById(ingredientId);
        } catch (FeignException.NotFound e) {
            throw new IngredientNotFoundException("ingredientId:" + ingredientId);
        }
    }

    private PetResponse getPetById(int userId, int petId) throws PetNotFoundException {
        try {
            return petClient.getPetById(userId, petId);
        } catch (FeignException.NotFound e) {
            throw new PetNotFoundException("petId:" + petId);
        }
    }

    public Recipe generateRecipe(int userId, int petId) throws UserNotFoundException, PetNotFoundException, IOException {
        PetResponse pet = getPetById(userId, petId);
        log.info("pet is exist: " + pet.toString());
        Recipe recipe = new Recipe();
        recipe.setUserId(userId);

        while (true) {
            String AIRecipe = aiRecipeGenerator.generateRecipe(ingredientClient.getIngredient(), pet);
            List<RecipeIngredient> ingredients = new ArrayList<>();

            try {
                String[] lines = AIRecipe.split("\n");
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int ingredientId = Integer.parseInt(parts[0].split(":")[1].trim());
                        ingredientIsExist(ingredientId);
                        int weight = Integer.parseInt(parts[1].split(":")[1].trim());
                        ingredients.add(new RecipeIngredient(ingredientId, weight, recipe));
                    }
                }
            } catch (Exception e) {
                continue;
            }
            recipe.setIngredients(ingredients);
            break;
        }
        recipeRepository.save(recipe);
        return recipe;
    }
}
