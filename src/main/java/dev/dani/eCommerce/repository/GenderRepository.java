package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {

    Optional<Gender> findByGender(String gender);
}
