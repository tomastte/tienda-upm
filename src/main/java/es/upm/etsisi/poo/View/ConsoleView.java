package es.upm.etsisi.poo.View;

import es.upm.etsisi.poo.Model.Catalog;
import es.upm.etsisi.poo.Model.Product;
import es.upm.etsisi.poo.Model.Ticket;

public class ConsoleView {

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void showHelp() {
        final String MESSAGE = """
        Commands:
         prod add <id> "<name>" <category> <price>
         prod list
         prod update <id> NAME|CATEGORY|PRICE <value>
         prod remove <id>
         ticket new
         ticket add <prodId> <quantity>
         ticket remove <prodId>
         ticket print
         echo "<texto>"
         help
         exit
        
        Categories: MERCH, STATIONERY, CLOTHES, BOOKS, ELECTRONICS
        Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOKS 10%, ELECTRONICS 3%.""";
        System.out.println(MESSAGE);
    }

    public void showProduct(Product product) {
        System.out.println(product);
    }

    public void showTicket(Ticket ticket) {
        System.out.println(ticket);
    }

    public void showCatalog(Catalog catalog) {
        System.out.println(catalog);
    }
}