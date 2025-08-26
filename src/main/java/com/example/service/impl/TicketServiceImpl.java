package com.example.service.impl;

import com.example.dto.TicketDto;
import com.example.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    @Override
    public List<TicketDto> filterByRoute(List<TicketDto> tickets, String origin, String destination) {
        return tickets.stream()
                .filter(t -> origin.equals(t.origin()) && destination.equals(t.destination()))
                .collect(Collectors.toList());
    }
}