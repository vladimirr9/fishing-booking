package com.project.fishingbookingback.dto.response;

public class ClientsAdventureViewDTO {
    private Long id;
    private String imageUrl;
    private String name;
    private String adress;
    private String description;
    private String instructorsDescription;
    private double mark;
    private double price;
    private boolean subscribed;

    public ClientsAdventureViewDTO() {

    }

    public ClientsAdventureViewDTO(Long id, String imageUrl, String name, String adress, String description, String instructorsDescription, double mark, double price) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.instructorsDescription = instructorsDescription;
        this.mark = mark;
        this.price = price;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorsDescription() {
        return instructorsDescription;
    }

    public void setInstructorsDescription(String instructorsDescription) {
        this.instructorsDescription = instructorsDescription;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
