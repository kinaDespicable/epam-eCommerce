package dev.dani.eCommerce.service;

import dev.dani.eCommerce.model.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> fetchByRoleNames(String... roleNames);

}
