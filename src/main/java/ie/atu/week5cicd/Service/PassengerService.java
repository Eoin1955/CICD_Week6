package ie.atu.week5cicd.Service;

import ie.atu.week5cicd.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll() {
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id) {
        for(Passenger p : store) {
            if(p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Passenger create(Passenger p) {
        if(findById(p.getPassengerId()).isPresent()) {
            throw new IllegalStateException("Passenger with id " + p.getPassengerId() + " already exists");
        }
        store.add(p);
        return p;
    }

    public Optional<Passenger> update(Passenger passenger) {
        Optional<Passenger> maybePassenger = findById(passenger.getPassengerId());
        if (maybePassenger.isPresent()) {
            Passenger update = maybePassenger.get();
            update.setFirstName(passenger.getFirstName());
            update.setEmail(passenger.getEmail());
            return Optional.of(update);
        }
        else{
            return Optional.empty();
        }
    }

    public Optional<Passenger> deletebyId(String id) {
        for(Passenger p : store) {
            if(p.getPassengerId().equals(id)) {
                store.remove(p);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
