package com.innova.innova.service;

import com.innova.innova.dao.PersonRepository;
import com.innova.innova.dao.RoleRepository;
import com.innova.innova.dto.LoginResponse;
import com.innova.innova.entity.Person;
import com.innova.innova.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthenticationService {
    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AuthenticationService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, TokenService tokenService) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public Person register(String email, String password) {

        Optional<Person> foundPerson = personRepository.findPersonByUsername(email);
        if (foundPerson.isPresent()) {
            return null;
        }
        String encodedPassword = passwordEncoder.encode(password);
        Role personRole = roleRepository.findByAuthority("USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(personRole);

        Person person = new Person();
        person.setUsername(email);
        person.setPassword(encodedPassword);
        person.setAuthorities(roles);
        return personRepository.save(person);

    }
    public LoginResponse login(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwtToken(auth);
            return new LoginResponse(token);
        } catch (Exception ex){
            ex.printStackTrace();
            return new LoginResponse("");
        }
    }



}
