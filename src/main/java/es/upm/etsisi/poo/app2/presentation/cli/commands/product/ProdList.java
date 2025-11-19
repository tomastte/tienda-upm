package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

import java.util.List;

public class ProdList implements Command {

    private final ProductService productService;

    public ProdList(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod list";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Lists all registered products with their id, name, price and stock.";
    }

    @Override
    public void execute(List<String> params) {
        List<Product> products = this.productService.list();
        View.showList("Catalog:", products);
        View.show("prod list: ok");
    }
}