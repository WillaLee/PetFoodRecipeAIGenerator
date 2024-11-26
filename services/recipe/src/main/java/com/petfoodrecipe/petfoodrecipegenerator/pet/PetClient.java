package com.petfoodrecipe.petfoodrecipegenerator.pet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pet-service",
        url = "${application.config.pet-url}"
)
public interface PetClient {
    @GetMapping("/{id}/pets/id/{pet-id}")
    PetResponse getPetById(@PathVariable int id, @PathVariable("pet-id") int petId);
}