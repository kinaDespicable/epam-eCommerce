package dev.dani.eCommerce.service.implementation;

import dev.dani.eCommerce.model.entity.Role;
import dev.dani.eCommerce.repository.RoleRepository;
import dev.dani.eCommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> fetchByRoleNames(String... roleNames) {
        return roleRepository.findAllByRoleIn(roleNames);
    }

}
