package com.awesome.flights.controller;

import com.awesome.flights.model.entity.FlightDetails;
import com.awesome.flights.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/flights")
public class FlightsController {

    @Autowired
    FlightsService flightsService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Works !!";
    }

    @GetMapping("/{fromPort}/{toPort}")
    public ResponseEntity getFlightsFromTo(@PathVariable String fromPort, @PathVariable String toPort){
        List<FlightDetails> flightDetailsList =  flightsService.getFlightsFromTo(fromPort, toPort);
        return ResponseEntity.status(HttpStatus.OK).body(flightDetailsList);
    }
}
