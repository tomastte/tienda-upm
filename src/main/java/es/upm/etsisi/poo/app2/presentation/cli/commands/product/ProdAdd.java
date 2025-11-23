package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Category;
import es.upm.etsisi.poo.app2.data.model.shop.products.BasicProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.CustomProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.ProductService;

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
        String id = null;
        int index = 0;
        if (!params[index].startsWith("\"")) {
            id = params[index];
            index = 1;
        }
        StringBuilder name = new StringBuilder(params[index] + " ");
        if (!name.toString().trim().endsWith("\"")) {
            index++;
            while (!params[index].endsWith("\"")) {
                name.append(params[index]).append(" ");
                index++;
            }
        }
        name = new StringBuilder(name.toString().trim());
        name = new StringBuilder(name.substring(1, name.length() - 2));
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
            product = new BasicProduct(name.toString(), category, price);
        } else {
            product = new CustomProduct(name.toString(), category, price, numberTexts);
        }
        if (id==null) {
            this.productService.add(product);
        } else {
            this.productService.add(product, id);
        }
        this.view.showEntity(product);
        this.view.show("prod add: ok");
    }
}