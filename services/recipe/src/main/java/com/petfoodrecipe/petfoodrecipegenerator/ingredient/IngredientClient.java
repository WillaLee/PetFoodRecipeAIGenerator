package com.petfoodrecipe.petfoodrecipegenerator.ingredient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "ingredient-service",
        url = "${application.config.ingredient-url}"
)
public interface IngredientClient {
    @GetMapping("/id/{id}")
    IngredientResponse getIngredientById(@PathVariable("id") int id);

    @GetMapping
    List<IngredientResponse> getIngredient();
}