package com.home.smssender.example.repository;

import com.home.smssender.example.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}