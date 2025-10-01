package es.upm.etsisi.poo.Controller;

import es.upm.etsisi.poo.View.ConsoleView;

import java.util.Arrays;

public class CommandController {

    private final ProductController productController;
    private final TicketController ticketController;
    private final ConsoleView view;

    public CommandController(ProductController productController, TicketController ticketController, ConsoleView view) {
        this.productController = productController;
        this.ticketController = ticketController;
        this.view = view;
    }

    public void parseCommand(String input) {

        String[] tokens = input.trim().split(" ");
        if (tokens.length == 0) return;

        switch (tokens[0].toLowerCase()) {
            case "prod":
                handleProduct(tokens);
                break;
            case "ticket":
                handleTicket(tokens);
                break;
            case "help":
                view.showHelp();
                break;
            case "echo":
                handleEcho(tokens);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                view.showMessage("Unknown command: " + input);
                break;
        }

    }

}