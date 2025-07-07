package com.hzokbe.eigakan.controller.person;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.eigakan.service.person.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }
}
