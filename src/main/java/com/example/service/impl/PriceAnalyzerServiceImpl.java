package com.example.service.impl;

import com.example.dto.TicketDto;
import com.example.service.PriceAnalyzerService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceAnalyzerServiceImpl implements PriceAnalyzerService {
    @Override
    public BigDecimal average(List<TicketDto> tickets) {
        if(tickets.isEmpty()) return BigDecimal.ZERO;

        BigDecimal sum = tickets.stream().map(TicketDto::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(tickets.size()), 2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal median(List<TicketDto> tickets) {
        if(tickets.isEmpty()) return BigDecimal.ZERO;
        List<BigDecimal> prices = new ArrayList<>();
        tickets.forEach(ticket -> prices.add(ticket.price()));
        Collections.sort(prices);

        int size = prices.size();

        if(size % 2 == 0) {
            return prices.get(size / 2 - 1).add(prices.get(size / 2))
                    .divide(new BigDecimal(size), 2, RoundingMode.HALF_UP);
        } else {
            return prices.get(size / 2);
        }
    }
}
