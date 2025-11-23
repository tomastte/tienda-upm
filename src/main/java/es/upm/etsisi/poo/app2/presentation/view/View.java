package es.upm.etsisi.poo.app2.presentation.view;

import java.util.List;

public class View {

    public void show(String message) {
        System.out.println(message);
    }

    public void showTitle(String tittle) {
        System.out.println(tittle);
    }

    public void showCommandPrompt() {
        System.out.print("UPM->");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public <T> void showEntity(T item) {
        System.out.println(item);
    }

    public <T> void showList(String title, List<T> items) {
        if (items == null || items.isEmpty()) {
            this.show("No items available yet");
        } else {
            this.show(title);
            int index = 1;
            for (T item : items) {
                System.out.println(index + ". " + item);
                index++;
            }
        }
    }
}
