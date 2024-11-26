package com.petfoodrecipe.petfoodrecipegenerator.ingredient;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/ingredients")
public class IngredientResource {
    private IngredientRepository ingredientRepository;

    public IngredientResource(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Ingredient> retrieveAllIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public EntityModel<List<Ingredient>> retrieveIngredientByName(@PathVariable String name) throws IngredientNotFoundException {
        List<Ingredient> ingredients = ingredientRepository.findByNameContainingIgnoreCase(name);
        if (ingredients.isEmpty()) {
            throw new IngredientNotFoundException("name:" + name);
        }

        EntityModel<List<Ingredient>> entityModel = EntityModel.of(ingredients);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllIngredients());
        entityModel.add(link.withRel("all-ingredients"));
        return entityModel;
    }

    @GetMapping("/id/{ingredient-id}")
    public EntityModel<Ingredient> retrieveIngredientById(@PathVariable("ingredient-id") int ingredientId) throws IngredientNotFoundException {
        Optional<Ingredient> ingredients = ingredientRepository.findById(ingredientId);
        if (ingredients.isEmpty()) {
            throw new IngredientNotFoundException("ingredientId:" + ingredientId);
        }

        EntityModel<Ingredient> entityModel = EntityModel.of(ingredients.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllIngredients());
        entityModel.add(link.withRel("all-ingredients"));
        return entityModel;
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable int id) {
        ingredientRepository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createIngredient(@Validated @RequestBody Ingredient ingredient) {
        Ingredient savedIngredients = ingredientRepository.save(ingredient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(savedIngredients.getName()).toUri();
        return ResponseEntity.created(location).build();
    }
}
