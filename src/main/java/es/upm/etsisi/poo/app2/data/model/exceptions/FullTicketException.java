package es.upm.etsisi.poo.app2.data.model.exceptions;

public class FullTicketException extends RuntimeException {
    private static final String DESCRIPTION = "The ticket is full. The next item is out of bounds";

    public FullTicketException() {
        super(DESCRIPTION);
    }

    public FullTicketException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
