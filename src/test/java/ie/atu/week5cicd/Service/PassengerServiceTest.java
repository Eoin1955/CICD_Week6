package ie.atu.week5cicd.Service;

import ie.atu.week5cicd.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {

    private PassengerService service;

    @BeforeEach
    void setUp() {
        service  = new PassengerService();
    }

    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .passengerId("P1")
                .firstName("Paul")
                .email("Paul@atu.ie")
                .build();

                service.create(p);

                Optional<Passenger> found = service.findById("P1");
                assertTrue(found.isPresent());
                assertEquals("Paul", found.get().getFirstName());
    }

    @Test
    void duplicateIdThrows(){
        service.create(new Passenger("3", "ryan", "anan@gmail.com"));

        assertThrows(IllegalArgumentException.class, () ->
            service.create(new Passenger("3", "paul", "paul@atu.ie")));
    }

    @Test
    void UpdatePassenger(){
        Passenger passenger = new Passenger("1", "eoin", "agereoin@gmail.com");

        service.create(passenger);

        Passenger updatedPassenger = new Passenger("1", "eoinager", "eoinager@gmail.com");
        Optional<Passenger> updatedpassenger = service.update(updatedPassenger);
        assertTrue(updatedpassenger.isPresent());
        assertEquals("eoinager", updatedpassenger.get().getFirstName());
        assertEquals("eoinager@gmail.com", updatedpassenger.get().getEmail());
    }

    @Test
    void deletePassenger(){
        Passenger passenger = new Passenger("1", "eoinager", "eoinager@gmail.com");

        service.create(passenger);

        Optional<Passenger> deletePassnger = service.deletebyId("1");

        assertTrue(deletePassnger.isPresent());
        assertEquals("eoinager", deletePassnger.get().getFirstName());
        assertEquals("eoinager@gmail.com", deletePassnger.get().getEmail());
    }
}
