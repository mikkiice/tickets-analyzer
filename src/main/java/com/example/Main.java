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

        System.out.println("Минимальное время полета по перевозчикам:");
        analytics.minFlightTimes().forEach((carrier, minutes) ->
                System.out.println("  " + carrier + ": " + minutes + " мин."));

        System.out.println("\nСредняя цена по всем перевозчикам: " + analytics.averagePrice());
        System.out.println("Медиана по всем перевозчикам: " + analytics.medianPrice());
        System.out.println("Разница (средняя - медиана) по всем перевозчикам: " + analytics.difference());


        System.out.println("____________________________________________\nРазница между средней и медианной ценой по каждому перевозчику:");
        analytics.differenceByCarrier().forEach((carrier, diff) ->
                System.out.printf("  %s: %s%n", carrier, diff));

    }

}