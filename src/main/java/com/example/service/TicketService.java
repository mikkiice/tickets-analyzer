package com.example.service;

import com.example.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> filterByRoute(List<TicketDto> tickets, String origin, String destination);
}
