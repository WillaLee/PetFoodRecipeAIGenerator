package com.petfoodrecipe.petfoodrecipegenerator.pet.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Pet{
    private final static double MER_FACTOR_UNDER_5_MONTH = 3;
    private final static double MER_FACTOR_5_TO_12_MONTH = 2;
    private final static double MER_FACTOR_ADULT_MAINTENANCE_INTACT = 1.8;
    private final static double MER_FACTOR_ADULT_MAINTENANCE_SPAYED_NEUTERED = 1.6;
    private final static double MER_FACTOR_SENIOR = 1.4;
    private final static double MER_FACTOR_OBESE_PRONE = 1.4;
    private final static double MER_FACTOR_ADULT_WEIGHT_LOSS = 1;
    private final static double MER_FACTOR_ADULT_WEIGHT_GAIN = 1.4;

    public Dog() {

    }

    @Override
    public int calculateMER() {
        int RER = (int) (70 * Math.pow(this.getWeight(), 0.75));
        switch (this.getLifeStage()) {
            case GROWTH -> {
                if (this.getBirthDate().isAfter(LocalDate.now().minusMonths(5)))
                    this.MER = (int) (RER * MER_FACTOR_UNDER_5_MONTH);
                else
                    this.MER = (int) (RER * MER_FACTOR_5_TO_12_MONTH);
                break;
            }
            case ADULT_MAINTENANCE_INTACT -> {
                this.MER = (int) (RER * MER_FACTOR_ADULT_MAINTENANCE_INTACT);
                break;
            }
            case ADULT_MAINTENANCE_SPAYED_NEUTERED -> {
                this.MER = (int) (RER * MER_FACTOR_ADULT_MAINTENANCE_SPAYED_NEUTERED);
                break;
            }
            case SENIOR -> {
                this.MER = (int) (RER * MER_FACTOR_SENIOR);
                break;
            }
            case OBESE_PRONE -> {
                this.MER = (int) (RER * MER_FACTOR_OBESE_PRONE);
                break;
            }
            case ADULT_WEIGHT_LOSS -> {
                this.MER = (int) (RER * MER_FACTOR_ADULT_WEIGHT_LOSS);
                break;
            }
            case ADULT_WEIGHT_GAIN -> {
                this.MER = (int) (RER * MER_FACTOR_ADULT_WEIGHT_GAIN);
                break;
            }
        }
        return this.MER;
    }
}
