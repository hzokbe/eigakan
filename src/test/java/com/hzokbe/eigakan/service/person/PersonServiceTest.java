package com.hzokbe.eigakan.service.person;

import com.hzokbe.eigakan.repository.person.PersonRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;
}
