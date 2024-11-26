package com.petfoodrecipe.petfoodrecipegenerator.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IngredientNotFoundException extends RuntimeException{

    public IngredientNotFoundException(String s) {
        super(s);
    }
}
