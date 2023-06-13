package formacion.block16springcloud.cliente.application;

import formacion.block16springcloud.cliente.controller.dto.ClienteInputDto;
import formacion.block16springcloud.cliente.controller.dto.ClienteOutputDto;
import formacion.block16springcloud.cliente.domain.Cliente;
import formacion.block16springcloud.cliente.repository.ClienteRepository;
import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutputDto obtenerClientePorId(int id) throws EntityNotFoundException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado un cliente con el id: " + id);

        return optionalCliente.get().clienteToClienteOutput();
    }

    @Override
    public List<ClienteOutputDto> obtenerClientes() {
        return clienteRepository.findAll().stream().map(Cliente::clienteToClienteOutput).toList();
    }

    @Override
    public ClienteOutputDto agregarCliente(ClienteInputDto cliente) throws UnprocessableEntityException {
        comprobarDatosCliente(cliente);
        return clienteRepository.save(new Cliente(cliente)).clienteToClienteOutput();
    }


    @Override
    public ClienteOutputDto modificarCliente(int id, ClienteInputDto cliente) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado un cliente con el id: " + id);

        comprobarDatosCliente(cliente);

        Cliente oldCliente = optionalCliente.get();
        oldCliente.setNombre(cliente.getNombre());
        oldCliente.setApellido(cliente.getApellido());
        oldCliente.setEdad(cliente.getEdad());
        oldCliente.setEmail(cliente.getEmail());
        oldCliente.setTelefono(cliente.getTelefono());

        return clienteRepository.save(oldCliente).clienteToClienteOutput();
    }

    @Override
    public void borrarCliente(int id) throws EntityNotFoundException {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado un cliente con el id: " + id);

        clienteRepository.deleteById(id);
    }

    private void comprobarDatosCliente(ClienteInputDto cliente) throws UnprocessableEntityException {
        if (cliente.getNombre() == null) throw new UnprocessableEntityException("El nombre es requerido");
        if (cliente.getApellido() == null) throw new UnprocessableEntityException("El apellido es requerido");
        if (cliente.getEdad() == null) throw new UnprocessableEntityException("La edad es requerido");
        if (cliente.getEmail() == null) throw new UnprocessableEntityException("El email es requerido");
        if (cliente.getTelefono() == null) throw new UnprocessableEntityException("El telefono es requerido");
    }
}
