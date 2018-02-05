package com.home.smssender.example.domain;

/*
*   SMS - Short Message Service
*   length of one sms is 160 characters
*   if text is longer needs to be spilt in two
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SMS {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "FROM")
    private String from;
    @Column(name = "TO")
    private List<String> to;
    @Column(name = "MESSAGE_TEXT")
    private String message;
    @Column(name = "MESSAGE_LENGTH")
    private int messageLength;
    @Column(name = "MESSAGE_SEND_TIME")
    private LocalDateTime messageSendTime;

    public SMS() {
    }

    public SMS(Long id, String from, List<String> to, String message) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.messageLength = message.length() / 160 + 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.messageLength = message.length() / 160 + 1;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public LocalDateTime getMessageSendTime() {
        return messageSendTime;
    }

    public void setMessageSendTime(LocalDateTime messageSendTime) {
        this.messageSendTime = messageSendTime;
    }
}
