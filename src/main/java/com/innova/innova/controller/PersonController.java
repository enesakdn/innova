package com.innova.innova.controller;

import com.innova.innova.entity.Person;
import com.innova.innova.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        try {
            Person savedPerson = personService.createPerson(person);
            return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        try {
            Person person = personService.getPersonById(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
        try {
            Person deletedPerson = personService.deletePerson(id);
            return new ResponseEntity<>(deletedPerson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/me")
    public Person getPersonByUsername(@AuthenticationPrincipal Person person) {
        return personService.findByUsername(person.getUsername());
    }
}
