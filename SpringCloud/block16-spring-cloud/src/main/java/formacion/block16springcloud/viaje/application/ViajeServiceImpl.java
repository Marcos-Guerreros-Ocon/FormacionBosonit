package formacion.block16springcloud.viaje.application;

import formacion.block16springcloud.cliente.domain.Cliente;
import formacion.block16springcloud.cliente.repository.ClienteRepository;
import formacion.block16springcloud.exception.EntityNotFoundException;
import formacion.block16springcloud.exception.UnprocessableEntityException;
import formacion.block16springcloud.viaje.controller.dto.ViajeInputDto;
import formacion.block16springcloud.viaje.controller.dto.ViajeOutputDto;
import formacion.block16springcloud.viaje.domain.Viaje;
import formacion.block16springcloud.viaje.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeServiceImpl implements ViajeService {
    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ViajeOutputDto agregarViaje(ViajeInputDto viaje) throws UnprocessableEntityException {
        comprobarViaje(viaje);

        return viajeRepository.save(new Viaje(viaje)).viajeToViajeOutputDto();
    }


    @Override
    public ViajeOutputDto obtenerViajePorId(int id) throws EntityNotFoundException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(id);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + id);

        return optionalViaje.get().viajeToViajeOutputDto();

    }

    @Override
    public List<ViajeOutputDto> obtenerViajes() {
        return viajeRepository.findAll().stream().map(Viaje::viajeToViajeOutputDto).toList();
    }

    @Override
    public ViajeOutputDto modificarViaje(int id, ViajeInputDto viaje) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(id);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + id);

        comprobarViaje(viaje);
        Viaje oldViaje = optionalViaje.get();
        oldViaje.setOrigin(viaje.getOrigin());
        oldViaje.setDestination(viaje.getDestination());
        oldViaje.setDepartureDate(viaje.getDepartureDate());
        oldViaje.setArrivalDate(viaje.getArrivalDate());

        return viajeRepository.save(oldViaje).viajeToViajeOutputDto();
    }

    @Override
    public void borrarViajePorId(int id) throws EntityNotFoundException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(id);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + id);

        viajeRepository.deleteById(id);

    }

    @Override
    public String agregarPasajero(int idViaje, int idCliente) throws EntityNotFoundException {
        // 1º Comprobamos que el viaje existe
        Optional<Viaje> optionalViaje = viajeRepository.findById(idViaje);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + idViaje);

        // 2º Comprobamos que el cliente existe
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        if (optionalCliente.isEmpty()) throw new EntityNotFoundException("No existe un cliente con id: " + idCliente);

        // 3º Obtenemos los datos
        Viaje viaje = optionalViaje.get();
        Cliente cliente = optionalCliente.get();
        List<Cliente> clientesViaje = viaje.getPassengers();

        // 4º Comprobamos que quepan pasajeros y que el pasajero no esté ya
        if (clientesViaje.size() == 40 || viaje.getStatus().equals("Ocupado"))
            return "No caben más pasajeros en el viaje";
        if (clientesViaje.contains(cliente)) return "El pasajero indicado ya está en el viaje indicado";

        // 5º Agregamos el pasajero
        clientesViaje.add(cliente);
        viaje.setPassengers(clientesViaje);
        if (clientesViaje.size() == 40) viaje.setStatus("Ocupado");
        // 6º Guardamos los cambios
        viajeRepository.save(viaje);

        return "Cliente con id: " + idCliente + " ha sido agregado con exito al viaje con id: " + idViaje;
    }

    @Override
    public String cambiarStatus(int idViaje, String status) throws EntityNotFoundException, UnprocessableEntityException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(idViaje);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + idViaje);

        Viaje viaje = optionalViaje.get();
        if (!status.equals("Disponible") && !status.equals("Ocupado"))
            throw new UnprocessableEntityException("Los estados son 'Disponible' u 'Ocupado'");

        viaje.setStatus(status);
        viajeRepository.save(viaje).viajeToViajeOutputDto();

        return "Status cambiado a " + status;
    }

    @Override
    public int contarPasajerosViaje(int idViaje) throws EntityNotFoundException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(idViaje);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + idViaje);

        return optionalViaje.get().getPassengers().size();

    }

    @Override
    public String verificarViaje(int idViaje) throws EntityNotFoundException {
        Optional<Viaje> optionalViaje = viajeRepository.findById(idViaje);
        if (optionalViaje.isEmpty()) throw new EntityNotFoundException("No existe un viaje con id: " + idViaje);

        return optionalViaje.get().getStatus();
    }

    private void comprobarViaje(ViajeInputDto viaje) throws UnprocessableEntityException {
        if (viaje.getOrigin() == null) throw new UnprocessableEntityException("El campo origin es requerido");
        if (viaje.getDestination() == null) throw new UnprocessableEntityException("El campo destination es requerido");
        if (viaje.getDepartureDate() == null)
            throw new UnprocessableEntityException("El campo departure date es requerido");
        if (viaje.getArrivalDate() == null)
            throw new UnprocessableEntityException("El campo arrival date es requerido");
    }

}
