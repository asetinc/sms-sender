package com.home.smssender.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Email {

    // This is base class
    // that will be extended by email templates
    // so each template will have its form, pictures and
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "FROM")
    private String from;
    @Column(name = "TO")
    private List<String> to;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "BODY")
    private String body;

    public Email() {
    }

    public Email(Long id, String from, List<String> to, String title, String body) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.title = title;
        this.body = body;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
