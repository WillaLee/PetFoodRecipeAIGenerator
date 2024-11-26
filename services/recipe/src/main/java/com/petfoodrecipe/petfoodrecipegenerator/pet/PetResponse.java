package com.petfoodrecipe.petfoodrecipegenerator.pet;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "cat"),
        @JsonSubTypes.Type(value = Dog.class, name = "dog")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PetResponse{
    private String name;
    private String lifeStage;
    private int MER;

    public PetResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeStage() {
        return lifeStage;
    }

    public void setLifeStage(String lifeStage) {
        this.lifeStage = lifeStage;
    }

    public int getMER() {
        return MER;
    }

    public void setMER(int MER) {
        this.MER = MER;
    }

    @Override
    public String toString() {
        return "PetResponse{" +
                "name='" + name + '\'' +
                ", lifeStage='" + lifeStage + '\'' +
                ", MER=" + MER +
                '}';
    }
}
