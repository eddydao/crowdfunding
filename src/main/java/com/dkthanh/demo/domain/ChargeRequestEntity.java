package com.dkthanh.demo.domain;

import lombok.Data;

@Data
public class ChargeRequestEntity {
    private boolean status;
    private String details;

    public ChargeRequestEntity() {
        this.status = true; //default to true
    }

    public ChargeRequestEntity(boolean status) {
        this.status = status;
    }

    public ChargeRequestEntity(boolean status, String details) {
        this.status = status;
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", details='" + details + '\'' +
                '}';
    }
}
