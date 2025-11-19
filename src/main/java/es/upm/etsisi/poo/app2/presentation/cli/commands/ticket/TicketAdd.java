package es.upm.etsisi.poo.app2.presentation.cli.commands.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.CustomProduct;
import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.data.model.shop.Ticket;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;
import es.upm.etsisi.poo.app2.services.user.CashierService;

import java.util.List;

public class TicketAdd implements Command {

    private final CashierService cashierService;
    private final ProductService productService;
    private final View view;

    public TicketAdd(View view, CashierService cashierService, ProductService productService) {
        this.cashierService = cashierService;
        this.productService = productService;
        this.view = view;
    }

    @Override
    public String name() {
        return "ticket add";
    }

    @Override
    public List<String> params() {
        return List.of("<ticketId>", "<cashId>", "<prodId>", "<amount>", "[--p<txt> --p<txt>]");
    }

    @Override
    public String helpMessage() {
        return "Implements a new ticket with ticketId, cashId, productId, amount and optional personalizations.";
    }

    @Override
    public void execute(List<String> params) {
        String ticketid = params.get(0);
        String cashid = params.get(1);
        Integer prodid = Integer.parseInt(params.get(2));
        Integer amount = Integer.parseInt(params.get(3));
        Product product = this.productService.findProd(prodid);
        Ticket ticket;
        if (product instanceof CustomProduct) {
            Product product1 = new CustomProduct(product);
            product1.setTexts(String.join(" ", params().subList(4, params().size())));
            ticket = this.cashierService.add(cashid, ticketid, product1, amount);
        } else {
            ticket = this.cashierService.add(cashid, ticketid, product, amount);
        }
        this.view.showEntity(ticket);
        this.view.show("ticket add: ok");
    }
}