package com.petfoodrecipe.petfoodrecipegenerator.pet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "cat"),
        @JsonSubTypes.Type(value = Dog.class, name = "dog")
})
@Entity(name = "pet_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "petType")
public abstract class Pet {
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;
    @Positive(message = "Weight should be a positive number")
    private float weight;
    @Past(message = "Birth date should be in the past")
    private LocalDate birthDate;
    private BodyCondition bodyCondition;
    private LifeStage lifeStage;
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    protected int MER;

    public Pet(String name, float weight, LocalDate birthDate, BodyCondition bodyCondition, LifeStage lifeStage, int userId) {
        this.name = name;
        this.weight = weight;
        this.birthDate = birthDate;
        this.bodyCondition = bodyCondition;
        this.lifeStage = lifeStage;
        this.userId = userId;
        this.MER = calculateMER();
    }

    public Pet() {
    }

    /**
     * calculate MER (maintenance energy requirement) for this pet
     * @return MER in Kcal
     */
    abstract public int calculateMER();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BodyCondition getBodyCondition() {
        return bodyCondition;
    }

    public void setBodyCondition(BodyCondition bodyCondition) {
        this.bodyCondition = bodyCondition;
    }

    public LifeStage getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(LifeStage lifeStage) {
        this.lifeStage = lifeStage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMER() {
        return MER;
    }

    public void setMER(int MER) {
        this.MER = MER;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", birthDate=" + birthDate +
                ", bodyCondition=" + bodyCondition +
                ", lifeStage=" + lifeStage +
                ", id=" + id +
                ", userId=" + userId +
                ", MER=" + MER +
                '}';
    }
}
