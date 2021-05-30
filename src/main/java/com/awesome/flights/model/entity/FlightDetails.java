package com.awesome.flights.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class FlightDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @Column(length = 6)
    String flightNumber;

    @Column(length = 3)
    String fromPort;

    @Column(length = 3)
    String toPort;

    @Column(length = 4)
    String startTime;

    @Column(length = 4)
    String endTime;
}
