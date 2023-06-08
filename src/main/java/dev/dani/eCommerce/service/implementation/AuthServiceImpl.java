package dev.dani.eCommerce.service.implementation;

import dev.dani.eCommerce.exception.PasswordMismatchException;
import dev.dani.eCommerce.exception.ResourceAlreadyExistException;
import dev.dani.eCommerce.model.dto.request.Validatable;
import dev.dani.eCommerce.model.dto.request.auth.RegistrationRequest;
import dev.dani.eCommerce.model.entity.User;
import dev.dani.eCommerce.repository.UserRepository;
import dev.dani.eCommerce.service.AuthService;
import dev.dani.eCommerce.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, Validatable<RegistrationRequest> {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public void register(RegistrationRequest registrationRequest) {

        checkExistenceForCreation(registrationRequest);
        passwordCheck(registrationRequest);

        var entity = User.builder()
                .firstName(registrationRequest.getFirstName().trim())
                .lastName(registrationRequest.getLastName().trim())
                .email(registrationRequest.getEmail().trim())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .roles(roleService.fetchByRoleNames(DEFAULT_ROLE))
                .dateOfBirth(registrationRequest.getDateOfBirth())
                .registrationDate(LocalDate.now())
                .isActive(true)
                .build();

        userRepository.save(entity);
    }

    @Override
    public void checkExistenceForCreation(RegistrationRequest request) throws ResourceAlreadyExistException {
        String email = request.getEmail().trim();
        if (userRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistException("Email " + email + " is already taken");
        }
    }

    public void passwordCheck(RegistrationRequest request) throws PasswordMismatchException {
        if (!request.getPassword().trim().equals(request.getMatchingPassword().trim())) {
            throw new PasswordMismatchException("Password does not match");
        }
    }


}

