package dev.dani.eCommerce.service;

import dev.dani.eCommerce.model.dto.request.auth.RegistrationRequest;

public interface AuthService {

    void register(RegistrationRequest registrationRequest);
}
