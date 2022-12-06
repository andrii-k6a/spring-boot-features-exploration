package org.kook.spring.boot.exploration.service;

import org.kook.spring.boot.exploration.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findById(Long id);

    Order newOrder(Order order);

    Order complete(Long id);

    Order cancel(Long id);
}
