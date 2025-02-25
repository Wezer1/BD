package com.example.demo.service;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.TicketDTO;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.Ticket;
import com.example.demo.exceptions.NoSuchException;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.repository.FlightRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    @Transactional
    public List<TicketDTO> getAllTicket() {
        log.info("Get all ticket");
        if (ticketRepository.findAll().isEmpty()) {
            throw new NoSuchException("No ticket");
        }
        return ticketRepository.findAll().stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public TicketDTO getTicketById(Integer ticketId) {
        log.info("Get ticket by id: {} ", ticketId);
        Optional<Ticket> ticketOptional = Optional.ofNullable(ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchException("There is no ticket with ID = " + ticketId + " in DB")));
        return ticketMapper.toDto(ticketOptional.get());
    }

    @Transactional
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        log.info("Saving ticket: {}", ticketDTO);
        if(userRepository.findById(ticketDTO.getUserId()).isEmpty()){
            throw new NoSuchException("There is no user with ID = "+ ticketDTO.getUserId() + " in Database");
        } else if (flightRepository.findById(ticketDTO.getFlightId()).isEmpty()) {
            throw new NoSuchException("There is no flight with ID = "+ ticketDTO.getFlightId() + " in Database");
        }else{
            Ticket savedTicket = ticketRepository.save(ticketMapper.toEntity(ticketDTO));
            return ticketMapper.toDto(savedTicket);
        }
    }

    @Transactional
    public TicketDTO changeTicket(Integer ticketId, TicketDTO ticketDTO){
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isEmpty()){
            throw new NoSuchException("There is no ticket with ID = "+ ticketId + " in Database");
        }else if(userRepository.findById(ticketDTO.getUserId()).isEmpty()){
            throw new NoSuchException("There is no user with ID = "+ ticketDTO.getUserId() + " in Database");
        } else if (flightRepository.findById(ticketDTO.getFlightId()).isEmpty()) {
            throw new NoSuchException("There is no flight with ID = "+ ticketDTO.getFlightId() + " in Database");
        }else{
            Ticket existingTicket = optionalTicket.get();
            Ticket updatedTicket = ticketMapper.toEntity(ticketDTO);
            existingTicket.setClas(updatedTicket.getClas());
            existingTicket.setSeat(updatedTicket.getSeat());
            existingTicket.setPrice(updatedTicket.getPrice());
            existingTicket.setUserId(updatedTicket.getUserId());
            existingTicket.setFlightId(updatedTicket.getFlightId());

            return ticketMapper.toDto(ticketRepository.save(existingTicket));
        }
    }

    @Transactional
    public void deleteTicket(Integer ticketId) {
        log.info("Delete ticket");
        if (ticketRepository.findById(ticketId).isEmpty()) {
            throw new NoSuchException("There is no ticket with ID = " + ticketId + " in Database");
        }
        ticketRepository.deleteById(ticketId);
    }
}
