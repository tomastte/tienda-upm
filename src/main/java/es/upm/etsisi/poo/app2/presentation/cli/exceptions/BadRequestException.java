package es.upm.etsisi.poo.app2.presentation.cli.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
