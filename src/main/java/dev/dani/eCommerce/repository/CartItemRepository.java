package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
