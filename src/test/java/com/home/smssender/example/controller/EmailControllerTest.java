package com.home.smssender.example.controller;

import com.home.smssender.example.domain.Email;
import com.home.smssender.example.repository.EmailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SmsSenderApplication.class)
@WebAppConfiguration
public class EmailControllerTest {

    @InjectMocks
    EmailController emailController;

    @Mock
    EmailRepository emailRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldStoreEmailIntoDatabase() {

        String sender = "sender@email.com";
        List<String> receivers = Arrays.asList("receiver_1@email.com", "receiver_2@email.com");
        String title = "Interesting title";
        String body = "Super short body";
        Email email = new Email(1L, sender, receivers, title, body);

        when(emailRepository.save(any(Email.class))).thenReturn(email);

        ResponseEntity<Email> response = emailController.createEmail(email);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(email));
        assertThat(response.getBody(), allOf(
                hasProperty("id", is(1L)),
                hasProperty("from", is(sender)),
                hasProperty("to", is(receivers)),
                hasProperty("title", is(title)),
                hasProperty("body", is(body))
        ));

        verify(emailRepository, times(1)).save(any(Email.class));
    }

    @Test
    public void shouldReturnEmailById() {
        String sender = "sender@email.com";
        List<String> receivers = Arrays.asList("receiver_1@email.com", "receiver_2@email.com");
        String title = "Interesting title";
        String body = "Super short body";
        Email email = new Email(1L, sender, receivers, title, body);

        when(emailRepository.findOne(any())).thenReturn(email);

        ResponseEntity<Email> response = emailController.getEmail(1L);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(email));

        verify(emailRepository, times(1)).findOne(any());
    }
}