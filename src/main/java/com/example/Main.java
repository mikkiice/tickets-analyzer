package com.example;

import com.example.config.AppConfig;
import com.example.dto.AnalyticsDto;
import com.example.dto.TicketDto;
import com.example.mapper.TicketMapper;
import com.example.model.Ticket;
import com.example.repository.JsonTicketRepository;
import com.example.service.AnalyticsService;
import com.example.service.TicketService;
import com.example.service.impl.AnalyticsServiceImpl;
import com.example.service.impl.FlightAnalyzerServiceImpl;
import com.example.service.impl.PriceAnalyzerServiceImpl;
import com.example.service.impl.TicketServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = AppConfig.get("tickets.file");

        JsonTicketRepository repo = new JsonTicketRepository(filePath);
        List<Ticket> allTickets = repo.findAll();

        List<TicketDto> dtos = allTickets.stream()
                .map(TicketMapper.INSTANCE::toDto)
                .toList();

        TicketService ticketService = new TicketServiceImpl();
        List<TicketDto> filtered = ticketService.filterByRoute(dtos, "VVO", "TLV");

        AnalyticsService analyticsService = new AnalyticsServiceImpl(
                new FlightAnalyzerServiceImpl(),
                new PriceAnalyzerServiceImpl()
        );
        AnalyticsDto analytics = analyticsService.analyze(filtered);

        System.out.println("=== Аналитика VVO → TLV ===");
        analytics.minFlightTimes().forEach((c, m) ->
                System.out.println("  " + c + ": " + m + " мин."));
        System.out.printf("Средняя цена: %s%n", analytics.averagePrice());
        System.out.printf("Медиана цены: %s%n", analytics.medianPrice());
        System.out.printf("Разница: %s%n", analytics.difference());
    }

}