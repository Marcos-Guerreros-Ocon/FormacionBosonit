package formacion.block7crudvalidationcors.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String message) {
        super("\nProblema: "+message+"\nCodigo:"+ HttpStatus.NOT_FOUND.value() +"\nHora:"+LocalDateTime.now());
    }
}
