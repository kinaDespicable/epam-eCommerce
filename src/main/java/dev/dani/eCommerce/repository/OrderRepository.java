package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
