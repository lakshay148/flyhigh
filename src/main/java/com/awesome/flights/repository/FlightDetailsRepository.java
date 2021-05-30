package com.awesome.flights.repository;

import com.awesome.flights.model.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails, String> {
}
