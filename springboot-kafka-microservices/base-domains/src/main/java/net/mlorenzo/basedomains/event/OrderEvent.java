package net.mlorenzo.basedomains.event;

import lombok.Data;
import net.mlorenzo.basedomains.dto.Order;

@Data
public class OrderEvent {

    private String message;
    private String status;
    private Order order;
}
