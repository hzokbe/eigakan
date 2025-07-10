package com.hzokbe.eigakan.service.person;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.exception.person.AlreadyRegisteredPersonException;
import com.hzokbe.eigakan.exception.person.InvalidPersonLastNameException;
import com.hzokbe.eigakan.exception.person.InvalidPersonNameException;
import com.hzokbe.eigakan.exception.person.PersonNotFoundException;
import com.hzokbe.eigakan.model.person.Person;
import com.hzokbe.eigakan.model.person.request.PersonRequest;
import com.hzokbe.eigakan.model.person.response.PersonResponse;
import com.hzokbe.eigakan.repository.person.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonResponse save(PersonRequest request) {
        var name = request.getName();

        if (name == null) {
            throw new InvalidPersonNameException("name cannot be null");
        }

        if (name.isBlank()) {
            throw new InvalidPersonNameException("name cannot be blank");
        }

        var lastName = request.getLastName();

        if (lastName == null) {
            throw new InvalidPersonLastNameException("last name cannot be null");
        }

        if (lastName.isBlank()) {
            throw new InvalidPersonLastNameException("last name cannot be blank");
        }

        name = name.trim();

        lastName = lastName.trim();

        if (repository.existsByNameAndLastName(name, lastName)) {
            throw new AlreadyRegisteredPersonException("person already registered");
        }

        var person = new Person(name, lastName);

        repository.save(person);

        return new PersonResponse(person.getId(), name, lastName);
    }

    public List<PersonResponse> findAll() {
        return repository.findAll().stream().map(p -> new PersonResponse(p.getId(), p.getName(), p.getLastName())).toList();
    }

    public PersonResponse findById(String id) {
        var optionalPerson = repository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException("person not found");
        }

        var person = optionalPerson.get();

        return new PersonResponse(person.getId(), person.getName(), person.getLastName());
    }

    public PersonResponse update(String id, PersonRequest request) {
        var name = request.getName();

        if (name == null) {
            throw new InvalidPersonNameException("name cannot be null");
        }

        if (name.isBlank()) {
            throw new InvalidPersonNameException("name cannot be blank");
        }

        name = name.trim();

        var lastName = request.getLastName();

        if (lastName == null) {
            throw new InvalidPersonNameException("last name cannot be null");
        }

        if (lastName.isBlank()) {
            throw new InvalidPersonNameException("last name cannot be blank");
        }

        lastName = lastName.trim();

        if (repository.existsByNameAndLastName(name, lastName)) {
            throw new AlreadyRegisteredPersonException("person already registered");
        }

        var optionalPerson = repository.findById(id);

        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException("person not found");
        }

        var person = optionalPerson.get();

        person.setName(name);

        person.setLastName(lastName);

        repository.save(person);

        return new PersonResponse(person.getId(), name, lastName);
    }
}
