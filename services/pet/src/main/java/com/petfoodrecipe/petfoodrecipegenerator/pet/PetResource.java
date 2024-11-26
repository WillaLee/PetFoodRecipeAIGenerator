package com.petfoodrecipe.petfoodrecipegenerator.pet;

import com.petfoodrecipe.petfoodrecipegenerator.pet.model.Pet;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/users/{id}/pets")
public class PetResource {
    private PetService petService;

    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> retrieveAllPetsForUser(@PathVariable int id) throws UserNotFoundException {
        return petService.getAllPets(id);
    }

    @GetMapping("/name/{pet-name}")
    public List<Pet> retrievePetsByNameForUser(@PathVariable int id, @PathVariable("pet-name") String petName) throws UserNotFoundException, PetNotFoundException {
        return petService.getAllPetsByName(id, petName);
    }

    @GetMapping("/id/{pet-id}")
    public Pet retrievePetsByIdForUser(@PathVariable int id, @PathVariable("pet-id") int petId) throws UserNotFoundException, PetNotFoundException {
        return petService.getPetById(id, petId);
    }

    @PostMapping
    public ResponseEntity<Object> createPetForUser(@PathVariable int id, @Validated @RequestBody Pet pet) throws UserNotFoundException {
        Pet savedPet = petService.save(pet, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(savedPet.getName()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{pet-id}")
    public ResponseEntity<Object> deletePet(@PathVariable int id, @PathVariable("pet-id") int petId) throws UserNotFoundException {
        petService.deletePet(id, petId);
        URI location = ServletUriComponentsBuilder.fromPath("/v1/users/{id}/pets").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public void deleteAllPetForUser(@PathVariable int id) {
        petService.deleteAllPetForUser(id);
    }
}
