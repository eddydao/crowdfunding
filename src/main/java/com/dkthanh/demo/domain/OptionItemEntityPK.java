package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OptionItemEntityPK implements Serializable {
    @Column(name = "option_id", nullable = false)
    private Integer optionId;
    @Column(name = "item_id", nullable = false)
    private Integer itemId;


}
