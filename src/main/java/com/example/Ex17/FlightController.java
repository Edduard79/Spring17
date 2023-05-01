package com.example.Ex17;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepo flightRepo;

    public FlightController(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    @PostMapping
    public void init() {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Flight flight = new Flight();
            flight.setDescription("Flight " + random.nextInt(100));
            flight.setFromAirport("Airport " + random.nextInt(10));
            flight.setToAirport("Airport " + random.nextInt(10));
            FlightStatus[] statuses = FlightStatus.values();
            flight.setStatus(statuses[random.nextInt(statuses.length)]);
            flightRepo.save(flight);
        }
    }
}