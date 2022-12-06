package org.kook.spring.boot.exploration.persistence;

import org.kook.spring.boot.exploration.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
