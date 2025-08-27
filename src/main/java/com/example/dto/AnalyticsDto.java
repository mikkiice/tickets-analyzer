package com.example.dto;

import java.math.BigDecimal;
import java.util.Map;

public record AnalyticsDto(
        Map<String, Long> minFlightTimes,
        BigDecimal averagePrice,
        BigDecimal medianPrice,
        BigDecimal difference,
        Map<String, BigDecimal> differenceByCarrier

) {}