package com.awesome.flights.service;

import com.awesome.flights.model.entity.FlightDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService implements IFlights{
    @Override
    public List<FlightDetails> getDirectFlights(String fromPort, String toPort) {
        return null;
    }

    @Override
    public List<FlightDetails> getFlightsWithHops(String fromPort, String toPort, int numberHops) {
        return null;
    }
}
