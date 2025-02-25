package com.example.demo.controller;


import com.example.demo.dto.TicketDTO;
import com.example.demo.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<TicketDTO>> getTicket(){
        return ResponseEntity.ok(ticketService.getAllTicket());
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Integer ticketId){
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> deleteTicket(@PathVariable Integer ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<TicketDTO> addTicket(@Valid @RequestBody TicketDTO ticketDTO){
        return ResponseEntity.ok(ticketService.saveTicket(ticketDTO));
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> changeTicket(@PathVariable Integer ticketId,
                                                      @Valid @RequestBody TicketDTO ticketDTO){
        return ResponseEntity.ok(ticketService.changeTicket(ticketId, ticketDTO));
    }
}
