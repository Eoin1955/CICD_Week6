package ie.atu.week5cicd.controller;

import ie.atu.week5cicd.Service.PassengerService;
import ie.atu.week5cicd.model.Passenger;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAll() {return ResponseEntity.ok(service.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable String id) {
        Optional<Passenger> passenger = service.findById(id);
        if (passenger.isPresent()) {
            return ResponseEntity.ok(passenger.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@Valid @RequestBody Passenger passenger) {
        Passenger created = service.create(passenger);
        return ResponseEntity
                .created(URI.create("/api/passengers" + created.getPassengerId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@Valid @RequestBody Passenger passenger) {
        Optional<Passenger> maybePassenger = service.update(passenger);
        if (maybePassenger.isPresent()) {
            return ResponseEntity.ok(maybePassenger.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable String id) {
        Optional<Passenger> passenger = service.findById(id);
        if (passenger.isPresent()) {
            service.deletebyId(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
