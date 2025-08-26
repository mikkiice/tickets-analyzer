package com.example.service;

import com.example.dto.AnalyticsDto;
import com.example.dto.TicketDto;

import java.util.List;

public interface AnalyticsService {
    AnalyticsDto analyze(List<TicketDto> tickets);
}
