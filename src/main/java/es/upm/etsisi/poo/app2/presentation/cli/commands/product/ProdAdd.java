package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.*;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

import java.util.List;

public class ProdAdd implements Command {

    private final ProductService productService;

    public ProdAdd(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String name() {
        return "prod add";
    }

    @Override
    public List<String> params() {
        return List.of("<id>", "\"<name>\"", "<category>", "<price>", "<numberTexts>");
    }

    @Override
    public String helpMessage() {
        return "Adds a new product to the catalog with optional id, name, category, price and optional max people.";
    }

    @Override
    public void execute(List<String> params) {
        Integer id = 0;
        int index = 0;
        if (!params.getFirst().startsWith("\"")) {
            id = Integer.valueOf(params.getFirst());
            index = 1;
        }
        String name = params().get(index) + " ";
        if (!name.trim().endsWith("\"")) {
            index++;
            while (!params().get(index).endsWith("\"")) {
                name += params().get(index) + " ";
                index++;
            }
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        Category category = Category.valueOf(params.get(index));
        index++;
        Double price = Double.valueOf(params.get(index));
        index++;
        Integer numberTexts = null;
        if (index < params.size()) {
            numberTexts = Integer.parseInt(params.get(index));
        }
        Product product;
        if (id == null) {
            if (numberTexts == null) {
                product = new BasicProduct(name, category, price);
            } else {
                product = new CustomProduct(name, category, price, numberTexts);
            }
            product = productService.add(product);
        } else {
            if (numberTexts == null) {
                product = new BasicProduct(id, name, category, price);
            } else {
                product = new CustomProduct(id, name, category, price, numberTexts);
            }
            product = productService.add(product, id);
        }
        View.showEntity(product);
        View.show("prod add: ok");
    }
}