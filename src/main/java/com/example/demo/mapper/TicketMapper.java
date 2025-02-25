package com.example.demo.mapper;

import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TicketMapper {

    public abstract Ticket toEntity(TicketDTO ticketDTO);

    public abstract TicketDTO toDto(Ticket ticket);
}
