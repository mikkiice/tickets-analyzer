package com.example.service;

import com.example.dto.TicketDto;

import java.util.List;
import java.util.Map;

public interface FlightAnalyzerService {
    Map<String, Long> minFlightTimes(List<TicketDto> tickets);
}
