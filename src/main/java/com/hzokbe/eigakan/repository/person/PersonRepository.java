package com.hzokbe.eigakan.repository.person;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hzokbe.eigakan.model.person.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
}
