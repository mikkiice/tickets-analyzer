package com.example.mapper;

import com.example.dto.TicketDto;
import com.example.model.Ticket;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);


    TicketDto toDto(Ticket ticket);

    Ticket toEntity(TicketDto dto);
}
