package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "option_item", schema = "demo", catalog = "")
public class OptionItemEntity {
    private Integer optionItemId;
    private Integer optionId;
    private String itemName;
    private Integer quantity;

    @Id
    @Column(name = "option_item_id", nullable = false)
    public Integer getOptionItemId() {
        return optionItemId;
    }

    public void setOptionItemId(Integer optionItemId) {
        this.optionItemId = optionItemId;
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
        OptionItemEntity that = (OptionItemEntity) o;
        return Objects.equals(optionItemId, that.optionItemId) &&
                Objects.equals(optionId, that.optionId) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionItemId, optionId, itemName, quantity);
    }
}
