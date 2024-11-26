package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends Exception{

    public RecipeNotFoundException(String s) {
        super(s);
    }
}
