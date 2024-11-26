package com.petfoodrecipe.petfoodrecipegenerator.airecipe;

import com.petfoodrecipe.petfoodrecipegenerator.pet.PetNotFoundException;
import com.petfoodrecipe.petfoodrecipegenerator.recipe.Recipe;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/v1/users/{id}/pet/{pet-id}/ai-recipes")
public class AIRecipeResource {

    private static final Logger log = LoggerFactory.getLogger(AIRecipeResource.class);
    private final AIRecipeService aiRecipeService;

    public AIRecipeResource(AIRecipeService aiRecipeService) {
        this.aiRecipeService = aiRecipeService;
    }

    @GetMapping
    public ResponseEntity<Object> generateRecipeForUser(@PathVariable int id, @PathVariable("pet-id") int petId) throws UserNotFoundException, IOException, PetNotFoundException {
        log.info("Generating recipe for pet " + petId);
        Recipe savedRecipe = aiRecipeService.generateRecipe(id, petId);
        URI location = ServletUriComponentsBuilder.fromPath("/v1/users/{id}/recipes/{recipe_id}").buildAndExpand(id, savedRecipe.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
