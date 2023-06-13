package formacion.block16springcloud.cliente.application;

import formacion.block16springcloud.cliente.controller.dto.ClienteInputDto;
import formacion.block16springcloud.cliente.controller.dto.ClienteOutputDto;
import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;

import java.util.List;

public interface ClienteService {

    ClienteOutputDto obtenerClientePorId(int id) throws EntityNotFoundException;

    List<ClienteOutputDto> obtenerClientes();

    ClienteOutputDto agregarCliente(ClienteInputDto cliente) throws UnprocessableEntityException;

    ClienteOutputDto modificarCliente(int id, ClienteInputDto cliente) throws EntityNotFoundException, UnprocessableEntityException;

    void borrarCliente(int id) throws EntityNotFoundException;
}