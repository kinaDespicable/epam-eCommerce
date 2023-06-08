package dev.dani.eCommerce.service;

import dev.dani.eCommerce.model.entity.Gender;

import java.util.Map;

public interface GenderService {

    Map<String, Gender> fetchGender();
    Gender fetchGender(String gender);
}
