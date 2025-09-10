package com.hzokbe.eigakan.service.person;

import com.hzokbe.eigakan.exception.person.AlreadyRegisteredPersonException;
import com.hzokbe.eigakan.exception.person.InvalidPersonLastNameException;
import com.hzokbe.eigakan.exception.person.InvalidPersonNameException;
import com.hzokbe.eigakan.model.person.request.PersonRequest;
import com.hzokbe.eigakan.repository.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
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

    @Test
    public void shouldThrowInvalidPersonNameException_whenSavingPersonWithBlankName() {
        var request = new PersonRequest("", "bar");

        assertThrows(InvalidPersonNameException.class, () -> service.save(request));

        request.setName(" ");

        assertThrows(InvalidPersonNameException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowInvalidPersonLastNameException_whenSavingPersonWithNullLastName() {
        var request = new PersonRequest("foo", null);

        assertThrows(InvalidPersonLastNameException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowInvalidPersonLastNameException_whenSavingPersonWithBlankLastName() {
        var request = new PersonRequest("foo", "");

        assertThrows(InvalidPersonLastNameException.class, () -> service.save(request));
    }

    @Test
    public void shouldThrowAlreadyRegisteredPersonException_whenSavingPersonAlreadyRegistered() {
        var request = new PersonRequest("foo", "bar");

        doReturn(true).when(repository).existsByNameAndLastName("foo", "bar");

        assertThrows(AlreadyRegisteredPersonException.class, () -> service.save(request));
    }
}
