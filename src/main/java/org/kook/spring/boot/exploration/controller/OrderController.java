package org.kook.spring.boot.exploration.controller;

import lombok.RequiredArgsConstructor;
import org.kook.spring.boot.exploration.assembler.OrderModelAssembler;
import org.kook.spring.boot.exploration.entity.Order;
import org.kook.spring.boot.exploration.service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderModelAssembler orderModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return orderModelAssembler.toCollectionModel(orders);
    }

    @GetMapping("/{id}")
    public EntityModel<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return orderModelAssembler.toModel(order);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {
        Order newOrder = orderService.newOrder(order);
        EntityModel<Order> entityModel = orderModelAssembler.toModel(newOrder);
        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).findById(newOrder.getId())).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id) {
        Order order = orderService.complete(id);
        return ResponseEntity.ok(orderModelAssembler.toModel(order));

    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        Order order = orderService.cancel(id);
        return ResponseEntity.ok(orderModelAssembler.toModel(order));
    }
}
