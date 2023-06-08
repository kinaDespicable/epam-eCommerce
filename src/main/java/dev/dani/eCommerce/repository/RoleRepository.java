package dev.dani.eCommerce.repository;

import dev.dani.eCommerce.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM user_role WHERE user_role.role IN (?)", nativeQuery = true)
    List<Role> findAllByRoleIn(@Param("roleNames") String... roleNames);
}
