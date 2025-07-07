package com.hzokbe.eigakan.service.person;

import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.repository.person.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
}
