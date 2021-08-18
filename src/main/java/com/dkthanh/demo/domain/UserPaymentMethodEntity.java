package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_payment_method", schema = "demo", catalog = "")
public class UserPaymentMethodEntity {
    private Integer userPaymentId;
    private Integer userId;
    private Integer paymentTypeId;
    private String paypalDetail;

    @Id
    @Column(name = "user_payment_id", nullable = false)
    public Integer getUserPaymentId() {
        return userPaymentId;
    }

    public void setUserPaymentId(Integer userPaymentId) {
        this.userPaymentId = userPaymentId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "payment_type_id", nullable = true)
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Basic
    @Column(name = "paypal_detail", nullable = true, length = 255)
    public String getPaypalDetail() {
        return paypalDetail;
    }

    public void setPaypalDetail(String paypalDetail) {
        this.paypalDetail = paypalDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPaymentMethodEntity that = (UserPaymentMethodEntity) o;
        return Objects.equals(userPaymentId, that.userPaymentId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(paymentTypeId, that.paymentTypeId) &&
                Objects.equals(paypalDetail, that.paypalDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userPaymentId, userId, paymentTypeId, paypalDetail);
    }
}
