package dev.dani.eCommerce.controller;

import dev.dani.eCommerce.config.security.SecurityUtils;
import dev.dani.eCommerce.exception.PasswordMismatchException;
import dev.dani.eCommerce.exception.ResourceAlreadyExistException;
import dev.dani.eCommerce.exception.ResourceNotFoundException;
import dev.dani.eCommerce.model.dto.request.auth.LoginRequest;
import dev.dani.eCommerce.model.dto.request.auth.RegistrationRequest;
import dev.dani.eCommerce.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static dev.dani.eCommerce.config.ApplicationConstants.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String signupForm(Model model) {

        Authentication authentication = SecurityUtils.getAuthentication();

        if (authentication == null || SecurityUtils.isAnonymousAuthenticationToken(authentication)) {
            model.addAttribute(PAGE_TITLE.getValue(), SIGNUP.getValue());
            model.addAttribute("request", new RegistrationRequest());
            return "signup";
        }
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("request") @Valid RegistrationRequest registrationRequest,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("failed", true);
            bindingResult.getFieldErrors()
                    .forEach(fieldError -> redirectAttributes.addAttribute(fieldError.getField(),
                            fieldError.getDefaultMessage()));
            return "redirect:signup";
        }
        authService.register(registrationRequest);
        return "redirect:login?registration=success";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        Authentication authentication = SecurityUtils.getAuthentication();

        if (authentication == null || SecurityUtils.isAnonymousAuthenticationToken(authentication)) {
            model.addAttribute(PAGE_TITLE.getValue(), LOGIN.getValue());
            model.addAttribute("request", new LoginRequest());
            return "login";
        }
        return "redirect:/";
    }

}
