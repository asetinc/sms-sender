package com.home.smssender.example.repository;

import com.home.smssender.example.domain.SMS;
import org.springframework.data.repository.CrudRepository;

public interface SMSRepository extends CrudRepository<SMS, Long> {
}
