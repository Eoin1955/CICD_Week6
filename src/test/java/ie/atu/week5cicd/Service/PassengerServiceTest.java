package ie.atu.week5cicd.Service;

import ie.atu.week5cicd.model.Passenger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

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
        service.create(Passenger.builder()
                .passengerId("P2")
                .firstName("Eoin")
                .email("e@gmail.com")
                .build());

        assertThrows(IllegalArgumentException.class, () ->
            service.create(Passenger.builder()
                    .passengerId("P2")
                    .firstName("Eoinie")
                    .email("ea@atu.ie")
                    .build()
        ));
    }
}
