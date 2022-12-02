package org.kook.spring.boot.exploration.assembler;

import org.kook.spring.boot.exploration.controller.EmployeeController;
import org.kook.spring.boot.exploration.entity.Employee;
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
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).findById(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));
    }

    @Override
    public CollectionModel<EntityModel<Employee>> toCollectionModel(Iterable<? extends Employee> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(collectingAndThen(toList(), this::toCollectionModel));
    }

    private CollectionModel<EntityModel<Employee>> toCollectionModel(List<EntityModel<Employee>> e) {
        return CollectionModel.of(e, linkTo(methodOn(EmployeeController.class).findAll()).withSelfRel());
    }
}
