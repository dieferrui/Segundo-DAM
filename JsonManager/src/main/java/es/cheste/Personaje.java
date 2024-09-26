package es.cheste;

import java.util.ArrayList;
import java.util.Objects;
import java.lang.Comparable;

public class Personaje implements Comparable<Personaje> {

    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private String homeworld;
    private ArrayList<String> films;
    private ArrayList<String> species;
    private ArrayList<String> vehicles;
    private ArrayList<String> starships;
    private String created;
    private String edited;
    private String url;

    public Personaje() {
        // Constructor vacío
    }

    @Override
    public int compareTo(Personaje otroPJ) {
        int length = Integer.compare(this.getFilms().length, otroPJ.getFilms().length);
        if (length == 0) {
            return this.getName().compareTo(otroPJ.getName());
        } else {
            return length;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<String> films) {
        this.films = films;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<String> getStarships() {
        return starships;
    }

    public void setStarships(ArrayList<String> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return getHeight() == personaje.getHeight() && getMass() == personaje.getMass() && Objects.equals(getName(), personaje.getName()) && Objects.equals(getHairColor(), personaje.getHairColor()) && Objects.equals(getSkinColor(), personaje.getSkinColor()) && Objects.equals(getEyeColor(), personaje.getEyeColor()) && Objects.equals(getBirthYear(), personaje.getBirthYear()) && Objects.equals(getGender(), personaje.getGender()) && Objects.equals(getHomeworld(), personaje.getHomeworld()) && Objects.equals(getFilms(), personaje.getFilms()) && Objects.equals(getSpecies(), personaje.getSpecies()) && Objects.equals(getVehicles(), personaje.getVehicles()) && Objects.equals(getStarships(), personaje.getStarships()) && Objects.equals(getCreated(), personaje.getCreated()) && Objects.equals(getEdited(), personaje.getEdited()) && Objects.equals(getUrl(), personaje.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHeight(), getMass(), getHairColor(), getSkinColor(), getEyeColor(), getBirthYear(), getGender(), getHomeworld(), getFilms(), getSpecies(), getVehicles(), getStarships(), getCreated(), getEdited(), getUrl());
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", mass=" + mass +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", homeworld='" + homeworld + '\'' +
                ", films=" + films +
                ", species=" + species +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
