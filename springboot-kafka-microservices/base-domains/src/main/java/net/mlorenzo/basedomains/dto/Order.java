package net.mlorenzo.basedomains.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {

    private String id;

    private String name;
    private int qty;
    private BigDecimal price;
}
