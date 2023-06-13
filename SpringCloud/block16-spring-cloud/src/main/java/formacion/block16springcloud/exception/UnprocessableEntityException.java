package formacion.block16springcloud.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class UnprocessableEntityException extends Exception{
    public UnprocessableEntityException(String message) {
        super("\nProblema: "+message+"\nCodigo:"+ HttpStatus.UNPROCESSABLE_ENTITY.value() +"\nHora:"+LocalDateTime.now());
    }
}
