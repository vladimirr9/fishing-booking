package com.project.fishingbookingback.dto.response;

import com.project.fishingbookingback.model.Client;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private boolean approved;
    private String imgUrl;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private double durationInHours;
    private double mark;
    private String address;
    private Boolean reportPresent;
    private Boolean reviewPresent;
    private Boolean complaintPresent;
    private Client client;
    private Boolean ongoing;
    private Long entityId;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Boolean getOngoing() {
        return this.ongoing;
    }


    public void setOngoing() {
        this.ongoing = startDate.isBefore(LocalDateTime.now()) && endDate.isAfter(LocalDateTime.now());
    }

    public ReservationDTO() {
    }

    public Boolean getComplaintPresent() {
        return complaintPresent;
    }

    public void setComplaintPresent(Boolean complaintPresent) {
        this.complaintPresent = complaintPresent;
    }

    public Boolean getReviewPresent() {
        return reviewPresent;
    }

    public void setReviewPresent(Boolean reviewPresent) {
        this.reviewPresent = reviewPresent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(double durationInHours) {
        this.durationInHours = durationInHours;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getReportPresent() {
        return reportPresent;
    }

    public void setReportPresent(Boolean reportPresent) {
        this.reportPresent = reportPresent;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
