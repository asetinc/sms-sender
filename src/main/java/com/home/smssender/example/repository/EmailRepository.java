package com.home.smssender.example.repository;

import com.home.smssender.example.domain.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
}
