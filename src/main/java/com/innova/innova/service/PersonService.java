package com.innova.innova.service;


import com.innova.innova.entity.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person);
    Person deletePerson(Long id);
    Person getPersonById(Long id);

    List<Person> getAllPersons();
     public Person findByUsername(String username);
}
