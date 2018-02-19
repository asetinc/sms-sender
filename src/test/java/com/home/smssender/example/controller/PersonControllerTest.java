package com.home.smssender.example.controller;

import com.home.smssender.example.domain.Person;
import com.home.smssender.example.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPersistPersonInDatabase() {
        String firstName = "Ales";
        String lastName = "Setinc";
        String phoneNumber = "07788200999";
        String email = "ales.setinc@email.com";
        String adderss = "45 Milson Road W14 0LB, London";

        Person person = new Person(1L, firstName, lastName, phoneNumber, email, adderss);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        ResponseEntity<Person> response = personController.savePerson(person);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(person));
        assertThat(response.getBody(), allOf(
                hasProperty("firstName", is(firstName)),
                hasProperty("lastName", is(lastName)),
                hasProperty("phoneNumber", is(phoneNumber)),
                hasProperty("email", is(email)),
                hasProperty("address", is(adderss))
        ));

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    public void shouldReturnPersonById() {
        Long personId = 5L;
        Person person = new Person(personId, "Ales", "Setinc", "07788200999", "ales.setinc@email.com", "45 Milson Road W14 0LB, London");

        when(personRepository.findOne(personId)).thenReturn(person);

        ResponseEntity<Person> response = personController.getPersonById(personId);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(person));
        verify(personRepository, times(1)).findOne(5L);
    }
}