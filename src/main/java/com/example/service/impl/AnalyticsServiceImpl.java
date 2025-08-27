package com.example.service.impl;


import com.example.dto.AnalyticsDto;
import com.example.dto.TicketDto;
import com.example.service.AnalyticsService;
import com.example.service.FlightAnalyzerService;
import com.example.service.PriceAnalyzerService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final FlightAnalyzerService flightAnalyzerService;
    private final PriceAnalyzerService priceAnalyzerService;


    @Override
    public AnalyticsDto analyze(List<TicketDto> tickets) {
        Map<String, Long> minTimes = flightAnalyzerService.minFlightTimes(tickets);
        BigDecimal avg = priceAnalyzerService.average(tickets);
        BigDecimal median = priceAnalyzerService.median(tickets);
        Map<String, BigDecimal> diffByCarrier = priceAnalyzerService.differenceByCarrier(tickets);
        return new  AnalyticsDto(minTimes, avg, median, avg.subtract(median), diffByCarrier);
    }
}
