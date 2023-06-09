package dev.dani.eCommerce.service.roleService;

import dev.dani.eCommerce.model.entity.Role;
import dev.dani.eCommerce.repository.RoleRepository;
import dev.dani.eCommerce.service.RoleService;
import dev.dani.eCommerce.service.implementation.RoleServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    @Test
    public void testFetchByRoleNames() {
        RoleRepository roleRepository = mock(RoleRepository.class);
        RoleService roleService = new RoleServiceImpl(roleRepository);

        // Mock the repository's behavior
        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(new Role("ROLE_ADMIN"));
        expectedRoles.add(new Role("ROLE_USER"));
        when(roleRepository.findAllByRoleIn("ROLE_ADMIN", "ROLE_USER")).thenReturn(expectedRoles);

        // Call the service method
        List<Role> actualRoles = roleService.fetchByRoleNames("ROLE_ADMIN", "ROLE_USER");

        // Verify the repository method was called with the correct arguments
        verify(roleRepository).findAllByRoleIn("ROLE_ADMIN", "ROLE_USER");

        // Assert the result
        assertEquals(expectedRoles, actualRoles);
    }
}
