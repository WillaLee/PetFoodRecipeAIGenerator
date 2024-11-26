package com.petfoodrecipe.petfoodrecipegenerator.pet;

import com.petfoodrecipe.petfoodrecipegenerator.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findAllByUserId(long userId);
    List<Pet> findAllByUserIdAndNameContainingIgnoreCase(long user_id, String name);

    void deleteByUserId(int id);
}
