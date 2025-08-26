package com.example.dto;

import java.math.BigDecimal;

public record TicketDto(
        String origin,
        String destination,
        String carrier,
        String departureDate,
        String departureTime,
        String arrivalDate,
        String arrivalTime,
        BigDecimal price
) {}