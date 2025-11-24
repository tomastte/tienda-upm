package es.upm.etsisi.poo.app2.presentation.cli.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request exception";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}