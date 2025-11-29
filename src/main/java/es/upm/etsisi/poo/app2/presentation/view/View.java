package es.upm.etsisi.poo.app2.presentation.view;

import java.util.List;

public class View {

    public void show(String message) {
        System.out.println(message);
    }

    public void showClose() {
        System.out.println("Closing application.");
        System.out.println("Goodbye!");
    }

    public void showInit() {
        System.out.println("Welcome to the ticket module App.");
        System.out.println("Ticket module. Type 'help' to see commands.");
    }

    public void showCommandPrompt() {
        System.out.print("tUPM> ");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    public <T> void showEntity(T item) {
        System.out.println(item);
    }

    public <T> void showList(String title, List<T> items) {
        if (items == null || items.isEmpty()) {
            this.show("No items available yet");
        } else {
            this.show(title);
            for (T item : items) {
                System.out.println("\t" + item);
            }
        }
    }
}