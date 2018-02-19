package com.home.smssender.example.controller;

import com.home.smssender.example.domain.SMS;
import com.home.smssender.example.dto.SMSHistory;
import com.home.smssender.example.repository.SMSRepository;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SmsSenderApplication.class)
@WebAppConfiguration
public class SMSControllerTest {

    @InjectMocks
    private SMSController smsController;

    @Mock
    private SMSRepository smsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldStoreSmsToDatabase() {
        String sender = "07788200994";
        List<String> receiver = Arrays.asList("07508626313", "07508626313");
        String textMessage = "Please call me when you see this";
        SMS sms = new SMS(1L, sender, receiver, textMessage);

        when(smsRepository.save(any(SMS.class))).thenReturn(sms);

        ResponseEntity<SMS> response = smsController.saveSMS(sms);

        Matcher<Class> smsMatcher = allOf(
                hasProperty("to", equalTo(sender)),
                hasProperty("from", equalTo(receiver)));

        assertThat(response.getStatusCode(), Is.is(HttpStatus.CREATED));
        assertThat(response.getBody(), Is.is(sms));
        assertThat(response.getBody(), allOf(
                hasProperty("id", Is.is(1L)),
                hasProperty("from", Is.is(sender)),
                hasProperty("to", Is.is(receiver)),
                hasProperty("message", Is.is(textMessage)),
                hasProperty("messageLength", Is.is(calculateMessageLength(textMessage)))
        ));

        verify(smsRepository, times(1)).save(any(SMS.class));

    }

    @Test
    public void shouldRetrieveSmsHistory() {
        SMS sms1 = new SMS(1L, "07788200994", Arrays.asList("07508626313", "07508626313"), "This is first message");
        sms1.setMessageSendTime(LocalDateTime.now());
        SMS sms2 = new SMS(2L, "07788200994", Arrays.asList("07432334233"), "This is second message");
        sms2.setMessageSendTime(LocalDateTime.now());
        SMS sms3 = new SMS(3L, "07788200994", Arrays.asList("07343234999"), "This is third message");

        List<SMS> smsList = Arrays.asList(sms1, sms2, sms3);
        SMSHistory smsHistory = new SMSHistory(Arrays.asList(sms1, sms2), Arrays.asList(sms3));
        when(smsRepository.findAll()).thenReturn(smsList);

        ResponseEntity<SMSHistory> response = smsController.getSMSHistory();

        assertThat(response.getStatusCode(), Is.is(HttpStatus.OK));

        assertThat(response.getBody().getCountProcessed(), equalTo(2));
        assertThat(response.getBody().getProcessed(), allOf(hasItem(
                hasProperty("messageSendTime", Is.is(notNullValue()))
        )));

        assertThat(response.getBody().getCountUnprocessed(), Is.is(1));
        assertThat(response.getBody().getUnprocessed(), hasItem(
                hasProperty("messageSendTime", Is.is(nullValue()))
        ));
    }

    private int calculateMessageLength(String message) {
        return message.length() / 160 + 1;
    }
}