package com.innova.innova.service;


import com.innova.innova.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public UserService(PersonRepository memberRepository) {
        this.personRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findPersonByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member is not valid"));
    }
}

