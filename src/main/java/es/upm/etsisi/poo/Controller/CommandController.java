package es.upm.etsisi.poo.Controller;

import es.upm.etsisi.poo.Model.Category;
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
                this.handleProduct(tokens);
                break;
            case "ticket":
                this.handleTicket(tokens);
                break;
            case "help":
                this.view.showHelp();
                break;
            case "echo":
                this.handleEcho(tokens);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                this.view.showMessage("Unknown command: " + input);
                break;
        }

    }

    private void handleProduct(String[] tokens) {

        switch (tokens[1].toLowerCase()) {
            case "add":
                int idAdd = Integer.parseInt(tokens[2]);
                String name = tokens[3].replace("\"", "");
                Category category = Category.valueOf(tokens[4].toUpperCase());
                double price = Double.parseDouble(tokens[5]);
                this.productController.handleAdd(idAdd, name, category, price);
                break;
            case "list":
                this.productController.handleList();
                break;
            case "update":
                int idUpdate = Integer.parseInt(tokens[2]);
                String field = tokens[3];
                String value = tokens[4];
                this.productController.handleUpdate(idUpdate, field, value);
                break;
            case "remove":
                int idRemove = Integer.parseInt(tokens[2]);
                this.productController.handleRemove(idRemove);
                break;
            case default:
                this.view.showMessage("Unknown command: " + String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length)));
                break;
        }

    }

    private void handleTicket(String[] tokens) {

        switch (tokens[1].toLowerCase()) {
            case "new":
                this.ticketController.handleNew();
                break;
            case "add":
                int idAdd = Integer.parseInt(tokens[2]);
                int quantity = Integer.parseInt(tokens[3]);
                this.ticketController.handleAdd(idAdd, quantity);
                break;
            case "remove":
                int idRemove = Integer.parseInt(tokens[2]);
                this.ticketController.handleRemove(idRemove);
                break;
            case "print":
                this.ticketController.handlePrint();
                break;
            case default:
                this.view.showMessage("Unknown command: " + String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length)));
                break;
        }

    }

    private void handleEcho(String[] tokens) {
        this.view.showMessage(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
    }

}