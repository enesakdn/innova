package com.innova.innova.service;



import com.innova.innova.dao.PersonRepository;
import com.innova.innova.entity.Person;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person createPerson(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new RuntimeException("Error creating person", e);
        }
    }

    @Override
    public Person deletePerson(Long id) {
        try {
            Person deletePerson = personRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
            personRepository.deleteById(id);
            return deletePerson;
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person", e);
        }
    }

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @Override
    public Person findByUsername(String username) {
        Optional<Person> person = personRepository.findPersonByUsername(username);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new RuntimeException("Person not found with username: " + username);
        }
    }

}
