package org.kook.spring.boot.exploration.assembler;

import org.kook.spring.boot.exploration.controller.OrderController;
import org.kook.spring.boot.exploration.entity.Order;
import org.kook.spring.boot.exploration.entity.Status;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<Order> toModel(Order order) {
        EntityModel<Order> orderModel = EntityModel.of(order,
                linkTo(methodOn(OrderController.class).findById(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).findAll()).withRel("orders"));

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }

    @Override
    public CollectionModel<EntityModel<Order>> toCollectionModel(Iterable<? extends Order> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(collectingAndThen(toList(), this::toCollectionModel));
    }

    private CollectionModel<EntityModel<Order>> toCollectionModel(List<EntityModel<Order>> e) {
        return CollectionModel.of(e, linkTo(methodOn(OrderController.class).findAll()).withSelfRel());
    }
}
