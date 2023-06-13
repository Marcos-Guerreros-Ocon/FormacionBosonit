package formacion.block16springcloud.viaje.application;

import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;
import formacion.block16springcloud.viaje.controller.dto.ViajeInputDto;
import formacion.block16springcloud.viaje.controller.dto.ViajeOutputDto;

import java.util.List;

public interface ViajeService {

    ViajeOutputDto agregarViaje(ViajeInputDto viaje) throws UnprocessableEntityException;

    ViajeOutputDto obtenerViajePorId(int id) throws EntityNotFoundException;

    List<ViajeOutputDto> obtenerViajes();

    ViajeOutputDto modificarViaje(int id, ViajeInputDto viaje) throws EntityNotFoundException, UnprocessableEntityException;

    void borrarViajePorId(int id) throws EntityNotFoundException;

    String agregarPasajero(int idViaje,int idCliente) throws EntityNotFoundException;

    String cambiarStatus(int idViaje,String status) throws EntityNotFoundException, UnprocessableEntityException;

    int contarPasajerosViaje(int idViaje) throws EntityNotFoundException;

    String verificarViaje(int idViaje) throws EntityNotFoundException;

}
