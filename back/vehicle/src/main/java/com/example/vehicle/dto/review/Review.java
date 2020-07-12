package com.example.vehicle.dto.review;

import java.time.LocalDateTime;

public class Review {

    private Long id;

    private String text;

    private int rating;

    private Status status;

    private Long vehicleId;

    private Long userId;

    private LocalDateTime date;

    public Review() {
    }

    public Review(Long id, String text, int rating, Status status, Long vehicleId, Long userId, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.status = status;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", status=" + status +
                ", vehicleId=" + vehicleId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
