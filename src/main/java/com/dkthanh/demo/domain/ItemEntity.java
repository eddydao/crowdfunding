package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "demo", catalog = "")
public class ItemEntity {
    private Integer itemId;
    private Integer optionId;
    private String itemName;
    private Integer quantity;

    @Id
    @Column(name = "item_id", nullable = false)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "option_id", nullable = true)
    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "item_name", nullable = true, length = 255)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(itemId, that.itemId) &&
                Objects.equals(optionId, that.optionId) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, optionId, itemName, quantity);
    }
}
