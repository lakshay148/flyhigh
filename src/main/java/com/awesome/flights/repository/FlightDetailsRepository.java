package com.awesome.flights.repository;

import com.awesome.flights.model.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, UUID> {
    List<FlightDetails> findAllByFromPortAndToPort(String fromPort, String toPort);
    List<FlightDetails> findAllByFromPort(String fromPort);
}
