package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientClient;
import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientNotFoundException;
import com.petfoodrecipe.petfoodrecipegenerator.ingredient.IngredientResponse;
import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;
import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredientRepository;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserClient;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);
    private RecipeRepository recipeRepository;
    private RecipeIngredientRepository recipeIngredientRepository;
    private UserClient userClient;
    private IngredientClient ingredientClient;

    public RecipeService(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, UserClient userClient, IngredientClient ingredientClient) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.userClient = userClient;
        this.ingredientClient = ingredientClient;
    }

    private void userIsExist(int userId) throws UserNotFoundException {
        try {
            userClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("userId:" + userId);
        }
    }

    private void ingredientIsExist(int ingredientId) throws IngredientNotFoundException {
        try {
            ingredientClient.getIngredientById(ingredientId);
        } catch (FeignException.NotFound e) {
            throw new IngredientNotFoundException("ingredientId:" + ingredientId);
        }
    }

    public Recipe saveRecipe(int userId, RecipeRequest recipeRequest) throws UserNotFoundException {
        userIsExist(userId);

        Recipe recipe = new Recipe();
        recipe.setUserId(userId);
        for (RecipeIngredient ingredient : recipeRequest.getIngredient()) {
            ingredientIsExist(ingredient.getIngredientId());
            ingredient.setRecipe(recipe);
        }
        recipe.setIngredients(recipeRequest.getIngredient());
        recipe.setNutrition(calculateNutrition(recipe));
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes(int userId) throws UserNotFoundException {
        userIsExist(userId);
        return recipeRepository.findAllByUserId(userId);
    }

    public Recipe getRecipeById(int userId, int recipeId) throws UserNotFoundException, RecipeNotFoundException {
        userIsExist(userId);
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeNotFoundException("recipe id:" + recipeId);
        }
        return recipe.get();
    }

    public void deleteRecipe(int userId, int recipeId) throws UserNotFoundException {
        userIsExist(userId);
        recipeRepository.deleteById(recipeId);
    }

    private Nutrition calculateNutrition(Recipe recipe) {
        Nutrition nutrition = new Nutrition();

        for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
            IngredientResponse ingredient = ingredientClient.getIngredientById(recipeIngredient.getIngredientId());
            float weight = recipeIngredient.getWeightInGrams();

            nutrition.addWeight(weight);
            nutrition.addEnergy(ingredient.energy() * weight);
            nutrition.addProtein(ingredient.protein() * weight);
            nutrition.addFat(ingredient.fat() * weight);
            nutrition.addCalcium(ingredient.calcium() * weight);
            nutrition.addPhosphorus(ingredient.phosphorus() * weight);
            nutrition.addPotassium(ingredient.potassium() * weight);
            nutrition.addSodium(ingredient.sodium() * weight);
            nutrition.addMagnesium(ingredient.magnesium() * weight);
            nutrition.addIron(ingredient.iron() * weight);
            nutrition.addCopper(ingredient.copper() * weight);
            nutrition.addZinc(ingredient.zinc() * weight);
            nutrition.addManganese(ingredient.manganese() * weight);
            nutrition.addVitaminA(ingredient.vitaminA() * weight);
            nutrition.addVitaminD(ingredient.vitaminD() * weight);
            nutrition.addVitaminE(ingredient.vitaminE() * weight);
            nutrition.addVitaminB1(ingredient.vitaminB1() * weight);
            nutrition.addVitaminB2(ingredient.vitaminB2() * weight);
            nutrition.addVitaminB3(ingredient.vitaminB3() * weight);
            nutrition.addVitaminB5(ingredient.vitaminB5() * weight);
            nutrition.addVitaminB6(ingredient.vitaminB6() * weight);
            nutrition.addCholine(ingredient.choline() * weight);
        }
        return nutrition;
    }
}
