package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

import java.util.List;

public class ProdRemove implements Command {

    private final ProductService productService;

    public ProdRemove(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod remove";
    }

    @Override
    public List<String> params() {
        return List.of("<id>");
    }

    @Override
    public String helpMessage() {
        return "Deletes an existing product by its id.";
    }

    @Override
    public void execute(List<String> params) {
        if (params.size() != 1) {
            throw new CommandException("Usage: prod remove <id>");
        }
        String id = params.getFirst();
        Product product = this.productService.remove(id);
        View.showEntity(product);
        View.show("prod remove: ok");
    }
}