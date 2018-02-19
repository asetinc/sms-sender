package com.home.smssender.example.controller;

import com.home.smssender.example.domain.Email;
import com.home.smssender.example.repository.EmailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class EmailController {

    @Inject
    private EmailRepository emailRepository;

    @RequestMapping(value = "email", method = RequestMethod.POST)
    public ResponseEntity<Email> createEmail(Email email) {
        Email emailInstance = emailRepository.save(email);

        return new ResponseEntity<>(emailInstance, HttpStatus.CREATED);
    }

    @RequestMapping(value = "email/{id}", method = RequestMethod.GET)
    public ResponseEntity<Email> getEmail(@PathVariable Long id) {
        Email emailInstance = emailRepository.findOne(id);

        return new ResponseEntity<>(emailInstance, HttpStatus.OK);
    }
}
