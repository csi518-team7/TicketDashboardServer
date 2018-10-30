package us.team7pro.login.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.team7pro.login.Model.Ticket;
import us.team7pro.login.Repository.TicketRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TicketController {
    private TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/cool-tickets")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Ticket> goodTickets() {
        return ticketRepository.findAll().stream().filter(this::isGood).collect(Collectors.toList());
    }

    private boolean isGood(Ticket ticket) {
        return !ticket.getName().equals("Breakfast") &&
                !ticket.getName().equals("Dinner");
    }
}
