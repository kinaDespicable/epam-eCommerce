package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
