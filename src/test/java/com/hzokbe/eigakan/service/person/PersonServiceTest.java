package com.hzokbe.eigakan.service.person;

import com.hzokbe.eigakan.exception.person.InvalidPersonNameException;
import com.hzokbe.eigakan.model.person.request.PersonRequest;
import com.hzokbe.eigakan.repository.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Test
    public void shouldThrowInvalidPersonNameException_whenSavingPersonWithNullName() {
        var request = new PersonRequest(null, "bar");

        assertThrows(InvalidPersonNameException.class, () -> service.save(request));
    }
}
