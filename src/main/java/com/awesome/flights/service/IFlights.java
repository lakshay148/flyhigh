package com.awesome.flights.service;

import com.awesome.flights.model.entity.FlightDetails;

import java.util.List;
import java.util.Map;

public interface IFlights {
    List<Map<String, Map<String, Integer>>> getFlightsFromTo(String fromPort, String toPort);
    List<FlightDetails> getDirectFlights(String fromPort, String toPort);
    List<FlightDetails> getFlightsWithHops(String fromPort, String toPort, int numberHops);
    List<FlightDetails> getFlightsFromPort(String fromPort);

}
