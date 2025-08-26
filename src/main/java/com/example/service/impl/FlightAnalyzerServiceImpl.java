package com.example.service.impl;

import com.example.dto.TicketDto;
import com.example.service.FlightAnalyzerService;
import com.example.util.TimeUtil;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightAnalyzerServiceImpl implements FlightAnalyzerService {
    @Override
    public Map<String, Long> minFlightTimes(List<TicketDto> tickets) {
        Map<String, Long> result = new HashMap<>();

        for (TicketDto ticket : tickets) {
            long minutes = Duration.between(
                    TimeUtil.parse(ticket.departureDate(), ticket.departureTime()),
                    TimeUtil.parse(ticket.arrivalDate(), ticket.arrivalTime())
            ).toMinutes();
            result.merge(ticket.carrier(),minutes,Math::min);
        }
        return result;
    }
}
