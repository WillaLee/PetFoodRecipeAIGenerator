package com.petfoodrecipe.petfoodrecipegenerator.ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity(name = "ingredient_details")
public class Ingredient {
    @Id
    @GeneratedValue
    private int id;
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;
    @Positive(message = "Energy should be a positive number")
    private float energy;
    @Positive(message = "Protein should be a positive number")
    private float protein;
    @Positive(message = "Fat should be a positive number")
    private float fat;
    @Positive(message = "Calcium should be a positive number")
    private float calcium;
    @Positive(message = "phosphorus should be a positive number")
    private float phosphorus;
    @Positive(message = "potassium should be a positive number")
    private float potassium;
    @Positive(message = "sodium should be a positive number")
    private float sodium;
    @Positive(message = "magnesium should be a positive number")
    private float magnesium;
    @Positive(message = "iron should be a positive number")
    private float iron;
    @Positive(message = "copper should be a positive number")
    private float copper;
    @Positive(message = "zinc should be a positive number")
    private float zinc;
    @Positive(message = "manganese should be a positive number")
    private float manganese;
    @Positive(message = "vitaminA should be a positive number")
    private float vitaminA;
    @Positive(message = "vitaminD should be a positive number")
    private float vitaminD;
    @Positive(message = "vitaminE should be a positive number")
    private float vitaminE;
    @Positive(message = "vitaminB1 should be a positive number")
    private float vitaminB1;
    @Positive(message = "vitaminB2 should be a positive number")
    private float vitaminB2;
    @Positive(message = "vitaminB3 should be a positive number")
    private float vitaminB3;
    @Positive(message = "vitaminB5 should be a positive number")
    private float vitaminB5;
    @Positive(message = "vitaminB6 should be a positive number")
    private float vitaminB6;
    @Positive(message = "choline should be a positive number")
    private float choline;

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 2, message = "Name should have at least 2 characters") String getName() {
        return name;
    }

    public void setName(@Size(min = 2, message = "Name should have at least 2 characters") String name) {
        this.name = name;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    @Positive(message = "Protein should be a positive number")
    public float getProtein() {
        return protein;
    }

    public void setProtein(@Positive(message = "Protein should be a positive number") float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }

    public float getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(float phosphorus) {
        this.phosphorus = phosphorus;
    }

    public float getPotassium() {
        return potassium;
    }

    public void setPotassium(float potassium) {
        this.potassium = potassium;
    }

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    public float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(float magnesium) {
        this.magnesium = magnesium;
    }

    public float getIron() {
        return iron;
    }

    public void setIron(float iron) {
        this.iron = iron;
    }

    public float getCopper() {
        return copper;
    }

    public void setCopper(float copper) {
        this.copper = copper;
    }

    public float getZinc() {
        return zinc;
    }

    public void setZinc(float zinc) {
        this.zinc = zinc;
    }

    public float getManganese() {
        return manganese;
    }

    public void setManganese(float manganese) {
        this.manganese = manganese;
    }

    public float getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(float vitaminA) {
        this.vitaminA = vitaminA;
    }

    public float getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(float vitaminD) {
        this.vitaminD = vitaminD;
    }

    public float getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(float vitaminE) {
        this.vitaminE = vitaminE;
    }

    public float getVitaminB1() {
        return vitaminB1;
    }

    public void setVitaminB1(float vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }

    public float getVitaminB2() {
        return vitaminB2;
    }

    public void setVitaminB2(float vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }

    public float getVitaminB3() {
        return vitaminB3;
    }

    public void setVitaminB3(float vitaminB3) {
        this.vitaminB3 = vitaminB3;
    }

    public float getVitaminB5() {
        return vitaminB5;
    }

    public void setVitaminB5(float vitaminB5) {
        this.vitaminB5 = vitaminB5;
    }

    public float getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(float vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public float getCholine() {
        return choline;
    }

    public void setCholine(float choline) {
        this.choline = choline;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", energy=" + energy +
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
