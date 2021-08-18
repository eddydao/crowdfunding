package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "payment_type", schema = "demo", catalog = "")
public class PaymentTypeEntity {
    private Integer paymentTypeId;
    private String paymentTypeName;

    @Id
    @Column(name = "payment_type_id", nullable = false)
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Basic
    @Column(name = "payment_type-name", nullable = false, length = 255)
    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentTypeEntity that = (PaymentTypeEntity) o;
        return Objects.equals(paymentTypeId, that.paymentTypeId) &&
                Objects.equals(paymentTypeName, that.paymentTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentTypeId, paymentTypeName);
    }
}
