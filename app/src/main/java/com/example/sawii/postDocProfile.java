package com.example.sawii;

public class postDocProfile {
    private int id;
    private int doc_id;
    private int price;
    private String bio;
    private String country;
    private String  specialties;
    private int year_of_ex;

    public postDocProfile(int doc_id, int price, String bio, String country, String specialties, int year_of_ex) {
        this.doc_id = doc_id;
        this.price = price;
        this.bio = bio;
        this.country = country;
        this.specialties = specialties;
        this.year_of_ex = year_of_ex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public int getYear_of_ex() {
        return year_of_ex;
    }

    public void setYear_of_ex(int year_of_ex) {
        this.year_of_ex = year_of_ex;
    }
}
