package formacion.block16springcloud.cliente.controller;

import formacion.block16springcloud.cliente.application.ClienteService;
import formacion.block16springcloud.cliente.controller.dto.ClienteInputDto;
import formacion.block16springcloud.cliente.controller.dto.ClienteOutputDto;
import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<ClienteOutputDto> obtenerClientes() {
        return clienteService.obtenerClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> obtenerClientePorId(@PathVariable int id) {
        try {
            return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteOutputDto> agregarCliente(@RequestBody ClienteInputDto cliente) {
        URI location = URI.create("/cliente");
        try {
            return ResponseEntity.created(location).body(clienteService.agregarCliente(cliente));
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteOutputDto> modificarCliente(@PathVariable int id, @RequestBody ClienteInputDto cliente) {
        try {
            return ResponseEntity.ok(clienteService.modificarCliente(id, cliente));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarCliente(@PathVariable int id) {

        try {
            clienteService.borrarCliente(id);
            return ResponseEntity.ok().body("Cliente con id: " + id + " ha sido borrado con exito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
