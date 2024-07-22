
package com.innova.innova.controller;



import com.innova.innova.dto.LoginRequest;
import com.innova.innova.dto.LoginResponse;
import com.innova.innova.dto.RegistrationPerson;
import com.innova.innova.entity.Person;
import com.innova.innova.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Person register(@RequestBody RegistrationPerson registrationPerson){
        return authenticationService.register(registrationPerson.getUsername(),
                registrationPerson.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authenticationService.login(loginRequest.getUsername(),
                loginRequest.getPassword());
    }
}

