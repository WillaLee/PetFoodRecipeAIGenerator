package com.petfoodrecipe.petfoodrecipegenerator.pet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PetNotFoundException extends Exception{

    public PetNotFoundException(String s) {
        super(s);
    }
}
