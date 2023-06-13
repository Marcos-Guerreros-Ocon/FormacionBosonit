package formacion.block16springcloud.viaje.controller;


import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;
import formacion.block16springcloud.viaje.application.ViajeService;
import formacion.block16springcloud.viaje.controller.dto.ViajeInputDto;
import formacion.block16springcloud.viaje.controller.dto.ViajeOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class ViajeController {
    @Autowired
    ViajeService viajeService;

    @GetMapping
    public ResponseEntity<List<ViajeOutputDto>> obtenerViajes() {
        return ResponseEntity.ok(viajeService.obtenerViajes());
    }

    @GetMapping("/{idViaje}")
    public ResponseEntity<ViajeOutputDto> obtenerViajePorId(@PathVariable("idViaje") int idViaje) {
        try {
            return ResponseEntity.ok(viajeService.obtenerViajePorId(idViaje));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<ViajeOutputDto> agregarViaje(@RequestBody ViajeInputDto viaje) {
        URI location = URI.create("/trip");
        try {
            return ResponseEntity.created(location).body(viajeService.agregarViaje(viaje));
        } catch (UnprocessableEntityException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{idViaje}")
    public ResponseEntity<ViajeOutputDto> updateViaje(@PathVariable("idViaje") Integer idViaje, @RequestBody ViajeInputDto viaje) {
        try {
            return ResponseEntity.ok(viajeService.modificarViaje(idViaje, viaje));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idViaje}")
    public ResponseEntity<String> borraViajeById(@PathVariable("idViaje") Integer idViaje) {
        try {
            viajeService.borrarViajePorId(idViaje);
            return ResponseEntity.ok("Viaje con id: " + idViaje + " borrado con exito");
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addPassenger/{idviaje}/{idCliente}")
    public ResponseEntity<String> addPassenger(@PathVariable("idviaje") Integer idViaje,
                                               @PathVariable("idCliente") Integer idCliente) {
        try {
            return ResponseEntity.ok().body(viajeService.agregarPasajero(idViaje, idCliente));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/passenger/count/{idviaje}")
    public ResponseEntity<Integer> countPassengers(@PathVariable("idviaje") Integer idViaje) {

        try {
            return ResponseEntity.ok(viajeService.contarPasajerosViaje(idViaje));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{idviaje}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable("idviaje") Integer idViaje,
                                               @PathVariable("status") String status) {

        try {
            return ResponseEntity.ok(viajeService.cambiarStatus(idViaje, status));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        } catch (UnprocessableEntityException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/verify/{idviaje}")
    public ResponseEntity<String> verifyViaje(@PathVariable("idviaje") Integer idViaje) {

        try {
            return ResponseEntity.ok(viajeService.verificarViaje(idViaje));
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }


}