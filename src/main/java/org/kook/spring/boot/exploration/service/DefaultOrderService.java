package org.kook.spring.boot.exploration.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kook.spring.boot.exploration.entity.Order;
import org.kook.spring.boot.exploration.entity.Status;
import org.kook.spring.boot.exploration.exception.IllegalOrderStatusException;
import org.kook.spring.boot.exploration.exception.OrderNotFoundException;
import org.kook.spring.boot.exploration.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order newOrder(Order order) {
        order.setStatus(Status.IN_PROGRESS);
        return repository.save(order);
    }

    @Override
    public Order complete(Long id) {
        Order order = findById(id);
        validateOrderStatus(order);
        order.setStatus(Status.COMPLETED);
        repository.save(order);
        return order;
    }

    @Override
    public Order cancel(Long id) {
        Order order = findById(id);
        validateOrderStatus(order);
        order.setStatus(Status.CANCELLED);
        repository.save(order);
        return order;
    }

    private void validateOrderStatus(Order order) {
        if (order.getStatus() != Status.IN_PROGRESS) {
            throw new IllegalOrderStatusException(order.getStatus());
        }
    }
}
