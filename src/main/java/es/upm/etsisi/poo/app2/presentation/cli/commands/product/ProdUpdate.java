package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.ProductService;

import java.util.List;

public class ProdUpdate implements Command {

    private final ProductService productService;
    private final View view;

    public ProdUpdate(View view, ProductService productService) {
        this.productService = productService;
        this.view = view;
    }

    @Override
    public String name() {
        return "prod update";
    }

    @Override
    public List<String> params() {
        return List.of("<id>", "NAME|CATEGORY|PRICE", "<value>");
    }

    @Override
    public String helpMessage() {
        return "Updates an existing product by its id, modifying name, price or stock.";
    }

    @Override
    public void execute(String[] params) {
        String id = params[0];
        String field = params[1];
        String value = params[2];
        Product product = this.productService.update(id, field, value);
        this.view.showEntity(product);
        this.view.show("prod update: ok");
    }
}