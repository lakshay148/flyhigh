package com.awesome.flights.service;

import com.awesome.flights.model.entity.FlightDetails;
import com.awesome.flights.repository.FlightDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FlightsService implements IFlights{

    @Autowired
    FlightDetailsRepository repository;

    @Override
    public List<Map<String, Map<String, Integer>>> getFlightsFromTo(String fromPort, String toPort) {
        List<Map<String, Map<String, Integer>>> finalList = new ArrayList<>();
        List<FlightDetails> allFlightDetails = getFlightsFromPort(fromPort);
        List<FlightDetails> directFlights = allFlightDetails.stream().filter(flightDetails -> flightDetails.getToPort().equals(toPort)).collect(Collectors.toList());
        List<FlightDetails> nonDirectFlights = allFlightDetails.stream().filter(flightDetails -> !flightDetails.getToPort().equals(toPort)).collect(Collectors.toList());
        Map<String, Map<String, Integer>> finalDirectMap = new HashMap<>();

        HashMap<String , Integer> minFlightTimeMap = new HashMap<>();
        Map<String , Map<String, Integer>> finalHopMap = new HashMap<>();
        for(FlightDetails flightDetails: nonDirectFlights){
            List<FlightDetails> finalFlightDetails =  getDirectFlights(flightDetails.getToPort(), toPort);
            List<FlightDetails> possibleFlights = finalFlightDetails.stream().filter(flight -> Integer.parseInt(flight.getStartTime())>(Integer.parseInt(flightDetails.getEndTime().replace("\r",""))+120)).collect(Collectors.toList());
            FlightDetails possibleFlight = null;

            for(FlightDetails flightDetails1 : possibleFlights){
                if(minFlightTimeMap.containsKey(flightDetails.getFromPort()+"_"+flightDetails.getToPort()+"_"+flightDetails1.getToPort())){
                    Integer time = minFlightTimeMap.get(flightDetails.getFromPort()+"_"+flightDetails.getToPort()+"_"+flightDetails1.getToPort());
                    if(time > (Integer.parseInt(flightDetails1.getStartTime()) - Integer.parseInt(flightDetails.getEndTime().replace("\r", "")))){
                        minFlightTimeMap.put(flightDetails.getFromPort()+"_"+flightDetails.getToPort()+"_"+flightDetails1.getToPort(), Integer.parseInt(flightDetails1.getStartTime()) - Integer.parseInt(flightDetails.getEndTime().replace("\r","")));
                        possibleFlight = flightDetails1;
                    }
                } else {
                    minFlightTimeMap.put(flightDetails.getFromPort()+"_"+flightDetails.getToPort()+"_"+flightDetails1.getToPort(),Integer.parseInt(flightDetails1.getStartTime()) - Integer.parseInt(flightDetails.getEndTime().replace("\r", "")) );
                    possibleFlight = flightDetails1;
                }
            }
            if(possibleFlight != null){
                HashMap<String, Integer> timeMap = new HashMap<>();
                timeMap.put(flightDetails.getFlightNumber()+"_"+possibleFlight.getFlightNumber(), Integer.parseInt(flightDetails.getEndTime().replace("\r", "")) - Integer.parseInt(flightDetails.getStartTime()));
                finalHopMap.put(flightDetails.getFromPort()+"_"+possibleFlight.getFromPort()+"_"+possibleFlight.getToPort(), timeMap);
            }
        }
        for(FlightDetails flightDetails : directFlights){
            Map<String, Integer> finalTimeMap = new HashMap<>();
            finalTimeMap.put(flightDetails.getFlightNumber(), Integer.parseInt(flightDetails.getEndTime().replace("\r", ""))-Integer.parseInt(flightDetails.getStartTime()));
            finalDirectMap.put(flightDetails.getFromPort()+"_"+flightDetails.getToPort(), finalTimeMap);
        }
        finalList.add(finalDirectMap);
        finalList.add(finalHopMap);
        return finalList;
    }

    @Override
    public List<FlightDetails> getDirectFlights(String fromPort, String toPort) {
        return repository.findAllByFromPortAndToPort(fromPort, toPort);
    }

    @Override
    public List<FlightDetails> getFlightsWithHops(String fromPort, String toPort, int numberHops) {
        return null;
    }

    @Override
    public List<FlightDetails> getFlightsFromPort(String fromPort) {
        return repository.findAllByFromPort(fromPort);
    }
}
