package com.home.smssender.example.controller;

import com.home.smssender.example.domain.Person;
import com.home.smssender.example.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.logging.Logger;

@RestController
public class PersonController {
    private final static Logger LOG = Logger.getLogger(PersonController.class.getName());

    @Inject
    PersonRepository personRepository;

    @RequestMapping(value = "person", method = RequestMethod.POST)
    public ResponseEntity<Person> savePerson(Person person) {
        LOG.info("savePerson invoked.");
        Person personInstance = personRepository.save(person);
        return new ResponseEntity<>(personInstance, HttpStatus.CREATED);
    }

    @RequestMapping(value = "person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        LOG.info("getPersonById invoked.");
        Person person = personRepository.findOne(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
