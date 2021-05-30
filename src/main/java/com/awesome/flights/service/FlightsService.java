package com.awesome.flights.service;

import com.awesome.flights.model.entity.FlightDetails;
import com.awesome.flights.repository.FlightDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService implements IFlights{

    @Autowired
    FlightDetailsRepository repository;

    @Override
    public List<FlightDetails> getFlightsFromTo(String fromPort, String toPort) {
        List<FlightDetails> flightDetails = getDirectFlights(fromPort, toPort);
        return flightDetails;
    }

    @Override
    public List<FlightDetails> getDirectFlights(String fromPort, String toPort) {
        return repository.findAllByFromPortAndToPort(fromPort, toPort);
    }

    @Override
    public List<FlightDetails> getFlightsWithHops(String fromPort, String toPort, int numberHops) {
        return null;
    }
}
