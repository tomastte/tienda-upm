package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.*;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

import java.util.List;

public class ProdAdd implements Command {

    private final ProductService productService;
    private final View view;

    public ProdAdd(View view, ProductService productService) {
        this.productService = productService;
        this.view = view;
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
    public void execute(String[] params) {
        Integer id = null;
        int index = 0;
        if (!params[index].startsWith("\"")) {
            id = Integer.valueOf(params[index]);
            index = 1;
        }
        String name = params[index] + " ";
        if (!name.trim().endsWith("\"")) {
            index++;
            while (!params[index].endsWith("\"")) {
                name += params[index] + " ";
                index++;
            }
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        Category category = Category.valueOf(params[index]);
        index++;
        Double price = Double.valueOf(params[index]);
        index++;
        Integer numberTexts = null;
        if (index < params.length) {
            numberTexts = Integer.parseInt(params[index]);
        }
        Product product;
        if (numberTexts == null) {
            product = new BasicProduct(name, category, price);
            product = this.productService.add(product);
        } else {
            product = new CustomProduct(name, category, price, numberTexts);
            product = this.productService.addCustom(product);
        }
        this.view.showEntity(product);
        this.view.show("prod add: ok");
    }
}