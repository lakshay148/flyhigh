package com.awesome.flights.service;

import com.awesome.flights.model.entity.FlightDetails;

import java.util.List;

public interface IFlights {
    List<FlightDetails> getDirectFlights(String fromPort, String toPort);
    List<FlightDetails> getFlightsWithHops(String fromPort, String toPort, int numberHops);
}
