package com.nisarga.jobportal.controller;

import com.nisarga.jobportal.entity.AppUser;
import com.nisarga.jobportal.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AppUserRepository users; private final PasswordEncoder encoder;
    public AuthController(AppUserRepository users, PasswordEncoder encoder){this.users=users;this.encoder=encoder;}
    @GetMapping("/login") public String login(){return "auth/login";}
    @GetMapping("/register") public String registerForm(Model model){model.addAttribute("user",new AppUser());return "auth/register";}
    @PostMapping("/register") public String register(@Valid @ModelAttribute("user") AppUser user, BindingResult result,
             @RequestParam(defaultValue="APPLICANT") String accountRole, Model model){
        if(users.existsByEmail(user.getEmail())) { result.rejectValue("email","duplicate","An account with this email already exists."); }
        if(user.getPassword()==null || user.getPassword().length()<6) result.rejectValue("password","short","Password must contain at least 6 characters.");
        if(result.hasErrors()) return "auth/register";
        user.setRole("EMPLOYER".equals(accountRole) ? "EMPLOYER" : "APPLICANT");
        user.setPassword(encoder.encode(user.getPassword())); users.save(user);
        return "redirect:/login?registered";
    }
}
