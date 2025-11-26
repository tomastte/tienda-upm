package es.upm.etsisi.poo.app2.services.exceptions;

public class NotFoundException extends RuntimeException {
    private static final String DESCRIPTION = "Not Found Exception. Object not found";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}