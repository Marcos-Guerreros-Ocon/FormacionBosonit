package formacion.block16springcloud.cliente.domain;

import formacion.block16springcloud.cliente.controller.dto.ClienteInputDto;
import formacion.block16springcloud.cliente.controller.dto.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private Integer telefono;

    public ClienteOutputDto clienteToClienteOutput() {
        ClienteOutputDto clienteOutputDto = new ClienteOutputDto();

        clienteOutputDto.setId(getId());
        clienteOutputDto.setNombre(getNombre());
        clienteOutputDto.setApellido(getApellido());
        clienteOutputDto.setEdad(getEdad());
        clienteOutputDto.setEmail(getEmail());
        clienteOutputDto.setTelefono(getTelefono());

        return clienteOutputDto;
    }

    public Cliente(ClienteInputDto clienteInputDto){
        setNombre(clienteInputDto.getNombre());
        setApellido(clienteInputDto.getApellido());
        setEdad(clienteInputDto.getEdad());
        setEmail(clienteInputDto.getEmail());
        setTelefono(clienteInputDto.getTelefono());
    }
}
