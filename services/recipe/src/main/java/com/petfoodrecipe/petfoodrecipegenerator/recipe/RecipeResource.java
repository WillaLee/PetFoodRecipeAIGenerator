package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import com.petfoodrecipe.petfoodrecipegenerator.recipeingredient.RecipeIngredient;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/users/{id}/recipes")
public class RecipeResource {
    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> retrieveAllRecipeForUser(@PathVariable int id) throws UserNotFoundException {
        return recipeService.getAllRecipes(id);
    }

    @GetMapping("/{recipeId}")
    public EntityModel<Recipe> retrieveRecipeByIdForUser(@PathVariable int id, @PathVariable int recipeId) throws UserNotFoundException, RecipeNotFoundException {
        EntityModel<Recipe> entityModel = EntityModel.of(recipeService.getRecipeById(id, recipeId));
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllRecipeForUser(id));
        entityModel.add(link.withRel("all-recipes"));
        return entityModel;
    }

    @PostMapping
    public ResponseEntity<Object> createRecipeForUser(@PathVariable int id, @Validated @RequestBody RecipeRequest recipeRequest) throws UserNotFoundException {
        Recipe savedRecipe = recipeService.saveRecipe(id, recipeRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{recipe_id}").buildAndExpand(savedRecipe.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable int id, @PathVariable int recipeId) throws UserNotFoundException {
        recipeService.deleteRecipe(id, recipeId);
        URI location = ServletUriComponentsBuilder.fromPath("/v1/users/{id}/recipes").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }
}
