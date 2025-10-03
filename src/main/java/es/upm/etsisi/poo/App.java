package es.upm.etsisi.poo;


import es.upm.etsisi.poo.Controller.CommandController;
import es.upm.etsisi.poo.Controller.ProductController;
import es.upm.etsisi.poo.Controller.TicketController;
import es.upm.etsisi.poo.Model.Catalog;
import es.upm.etsisi.poo.View.ConsoleView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ConsoleView view = new ConsoleView();
            ProductController productController = new ProductController(view);
            Catalog catalog = productController.getCatalog();
            TicketController ticketController = new TicketController(view, catalog);
            CommandController comand = new CommandController(productController, ticketController, view);
            String line = "";
            while (!line.equals("exit")) {
                System.out.print("tUPM> ");
                line = reader.readLine();
                comand.parseCommand(line);
            }
            System.out.println("Closing application.");
            System.out.println("Goodbye!");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}