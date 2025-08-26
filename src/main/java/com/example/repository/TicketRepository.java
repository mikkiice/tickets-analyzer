package com.example.repository;

import com.example.model.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> findAll() throws Exception;
}
