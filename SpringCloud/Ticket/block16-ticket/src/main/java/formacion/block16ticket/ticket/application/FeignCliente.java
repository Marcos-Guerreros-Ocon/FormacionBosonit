package formacion.block16ticket.ticket.application;

import formacion.block16ticket.ticket.controller.dto.TicketOutputDto;
import formacion.block16ticket.ticket.domain.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignCliente", url = "${feignCliente.url}")
public interface FeignCliente {
    @GetMapping("/cliente/{id}")
    public Cliente getClienteById(@PathVariable Integer id);
}