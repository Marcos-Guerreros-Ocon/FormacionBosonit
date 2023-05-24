package formacion.block7crudvalidation.exception;

import java.time.LocalDateTime;

public class UnprocessableEntityException extends Exception{
    public UnprocessableEntityException(String message, int httpCode, LocalDateTime timestamp) {
        super("\nProblema: "+message+"\nCodigo:"+httpCode+"\nHora:"+timestamp);
    }
}
