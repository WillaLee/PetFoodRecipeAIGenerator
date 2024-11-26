package com.petfoodrecipe.petfoodrecipegenerator.pet;

import com.petfoodrecipe.petfoodrecipegenerator.pet.model.Pet;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserClient;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserNotFoundException;
import com.petfoodrecipe.petfoodrecipegenerator.user.UserResponse;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private PetRepository petRepository;
    private UserClient userClient;

    public PetService(PetRepository petRepository, UserClient userClient) {
        this.petRepository = petRepository;
        this.userClient = userClient;
    }

    private void userIsExist(int userId) throws UserNotFoundException {
        try {
            userClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new UserNotFoundException("userId:" + userId);
        }
    }

    public Pet save(Pet pet, int userId) throws UserNotFoundException {
        userIsExist(userId);
        pet.setUserId(userId);
        pet.calculateMER();
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets(int userId) throws UserNotFoundException {
        userIsExist(userId);
        return petRepository.findAllByUserId(userId);
    }

    public Pet getPetById(int userId, int petId) throws UserNotFoundException, PetNotFoundException {
        userIsExist(userId);
        Optional<Pet> pet = petRepository.findById(petId);
        if (pet.isEmpty()) {
            throw new PetNotFoundException("pet id:" + petId);
        }
        return pet.get();
    }

    public List<Pet> getAllPetsByName(int userId, String petName) throws UserNotFoundException, PetNotFoundException {
        userIsExist(userId);
        List<Pet> pet = petRepository.findAllByUserIdAndNameContainingIgnoreCase(userId, petName);
        if (pet.isEmpty()) {
            throw new PetNotFoundException("pet name:" + petName);
        }
        return pet;
    }

    public void deletePet(int userId, int petId) throws UserNotFoundException {
        userIsExist(userId);
        petRepository.deleteById(petId);
    }

    public void deleteAllPetForUser(int id) {
        petRepository.deleteByUserId(id);
    }
}
