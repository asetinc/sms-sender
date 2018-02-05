package com.home.smssender.example.controller;

import com.home.smssender.example.domain.SMS;
import com.home.smssender.example.dto.SMSHistory;
import com.home.smssender.example.repository.SMSRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class SMSController {
    private final static Logger LOG = Logger.getLogger(SMSController.class.getName());

    @Inject
    private SMSRepository smsRepository;

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ResponseEntity<SMS> createSMS(SMS sms) {
        SMS smsInstance = smsRepository.save(sms);
        return new ResponseEntity<>(smsInstance, HttpStatus.CREATED);
    }

    @RequestMapping(value = "sms", method = RequestMethod.GET)
    public ResponseEntity<SMSHistory> getSMSHistory() {
        Iterable<SMS> allSMSs = smsRepository.findAll();
        List<SMS> processed = new ArrayList<SMS>();
        List<SMS> unprocessed = new ArrayList<SMS>();

        for (SMS sms : allSMSs) {
            if (sms.getMessageSendTime() != null) {
                processed.add(sms);
            } else {
                unprocessed.add(new SMS());
            }
        }

        return new ResponseEntity<>(new SMSHistory(processed, unprocessed), HttpStatus.OK);
    }


}
