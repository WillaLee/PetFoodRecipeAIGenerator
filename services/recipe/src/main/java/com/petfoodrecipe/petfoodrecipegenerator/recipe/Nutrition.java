package com.petfoodrecipe.petfoodrecipegenerator.recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "nutrition_details")
public class Nutrition {
    @Id
    @GeneratedValue
    private Long id;
    private float weight;
    private float energy;
    private float protein;
    private float fat;
    private float calcium;
    private float phosphorus;
    private float potassium;
    private float sodium;
    private float magnesium;
    private float iron;
    private float copper;
    private float zinc;
    private float manganese;
    private float vitaminA;
    private float vitaminD;
    private float vitaminE;
    private float vitaminB1;
    private float vitaminB2;
    private float vitaminB3;
    private float vitaminB5;
    private float vitaminB6;
    private float choline;

    public void addWeight(float weight) {this.weight += weight;}
    public void addEnergy(float value) { this.energy += value; }
    public void addProtein(float value) { this.protein += value; }
    public void addFat(float value) { this.fat += value; }
    public void addCalcium(float value) { this.calcium += value; }
    public void addPhosphorus(float value) { this.phosphorus += value; }
    public void addPotassium(float value) { this.potassium += value; }
    public void addSodium(float value) { this.sodium += value; }
    public void addMagnesium(float value) { this.magnesium += value; }
    public void addIron(float value) { this.iron += value; }
    public void addCopper(float value) { this.copper += value; }
    public void addZinc(float value) { this.zinc += value; }
    public void addManganese(float value) { this.manganese += value; }
    public void addVitaminA(float value) { this.vitaminA += value; }
    public void addVitaminD(float value) { this.vitaminD += value; }
    public void addVitaminE(float value) { this.vitaminE += value; }
    public void addVitaminB1(float value) { this.vitaminB1 += value; }
    public void addVitaminB2(float value) { this.vitaminB2 += value; }
    public void addVitaminB3(float value) { this.vitaminB3 += value; }
    public void addVitaminB5(float value) { this.vitaminB5 += value; }
    public void addVitaminB6(float value) { this.vitaminB6 += value; }
    public void addCholine(float value) { this.choline += value; }

    public float getEnergy() {
        return energy;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getCalcium() {
        return calcium;
    }

    public float getPhosphorus() {
        return phosphorus;
    }

    public float getPotassium() {
        return potassium;
    }

    public float getSodium() {
        return sodium;
    }

    public float getMagnesium() {
        return magnesium;
    }

    public float getIron() {
        return iron;
    }

    public float getCopper() {
        return copper;
    }

    public float getZinc() {
        return zinc;
    }

    public float getManganese() {
        return manganese;
    }

    public float getVitaminA() {
        return vitaminA;
    }

    public float getVitaminD() {
        return vitaminD;
    }

    public float getVitaminE() {
        return vitaminE;
    }

    public float getVitaminB1() {
        return vitaminB1;
    }

    public float getVitaminB2() {
        return vitaminB2;
    }

    public float getVitaminB3() {
        return vitaminB3;
    }

    public float getVitaminB5() {
        return vitaminB5;
    }

    public float getVitaminB6() {
        return vitaminB6;
    }

    public float getCholine() {
        return choline;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "energy=" + energy +
                ", protein=" + protein +
                ", fat=" + fat +
                ", calcium=" + calcium +
                ", phosphorus=" + phosphorus +
                ", potassium=" + potassium +
                ", sodium=" + sodium +
                ", magnesium=" + magnesium +
                ", iron=" + iron +
                ", copper=" + copper +
                ", zinc=" + zinc +
                ", manganese=" + manganese +
                ", vitaminA=" + vitaminA +
                ", vitaminD=" + vitaminD +
                ", vitaminE=" + vitaminE +
                ", vitaminB1=" + vitaminB1 +
                ", vitaminB2=" + vitaminB2 +
                ", vitaminB3=" + vitaminB3 +
                ", vitaminB5=" + vitaminB5 +
                ", vitaminB6=" + vitaminB6 +
                ", choline=" + choline +
                '}';
    }
}
