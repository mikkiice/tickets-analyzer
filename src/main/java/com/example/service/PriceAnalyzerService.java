package com.example.service;

import com.example.dto.TicketDto;

import java.math.BigDecimal;
import java.util.List;

public interface PriceAnalyzerService {
    BigDecimal average(List<TicketDto> tickets);
    BigDecimal median(List<TicketDto> tickets);
}
