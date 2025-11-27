package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Category;
import es.upm.etsisi.poo.app2.data.model.shop.products.BasicProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.CustomProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
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
        return List.of("[<id>]", "\"<name>\"", "<category>", "<price>", "[<numberTexts>]");
    }

    @Override
    public String helpMessage() {
        return "Adds a new product to the catalog with optional id, name, category, price and optional max people.";
    }

    @Override
    public String[] assessParams(String[] params) {
        int index = 0;
        // Id
        String id = null;
        if (params[0].matches("-?\\d+")) {
            id = params[0];
            index++;
        }
        // Name
        if (!params[index].startsWith("\""))
            throw new CommandException("Usage: " + this.help());
        StringBuilder name = new StringBuilder();
        name.append(params[index].substring(1));
        index++;
        while (index < params.length && !params[index].endsWith("\"")) {
            name.append(" ").append(params[index]);
            index++;
        }
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        name.append(" ").append(params[index], 0, params[index].length() - 1);
        index++;
        // Category
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        if (!params[index].equals("MERCH") && !params[index].equals("STATIONARY")
                && !params[index].equals("CLOTHES") && !params[index].equals("BOOK")
                && !params[index].equals("ELECTRONICS")) {
            throw new CommandException("Usage: " + this.help());
        }
        String category = params[index];
        index++;
        // Price
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        if (!params[index].matches("[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
            throw new CommandException("Usage: " + this.help());
        }
        String price = params[index];
        index++;
        // NumberTexts (optional)
        String numberTexts = null;
        if (index < params.length) {
            numberTexts = params[index];
            if (!numberTexts.matches("-?\\d+"))
                throw new CommandException("Usage: " + this.help());
        }
        // Return
        return new String[]{id, name.toString().trim(), category, price, numberTexts};
    }

    @Override
    public void execute(String[] params) {
        params = this.assessParams(params);
        String id = params[0];
        String name = params[1];
        Category category = Category.valueOf(params[2]);
        Double price = Double.parseDouble(params[3]);
        Integer numberTexts = null;
        if (params[4] != null) {
            numberTexts = Integer.parseInt(params[4]);
        }
        Product product;
        if (numberTexts == null) {
            product = new BasicProduct(name, category, price);
        } else {
            product = new CustomProduct(name, category, price, numberTexts);
        }
        if (id == null) {
            this.productService.add(product);
        } else {
            this.productService.add(product, id);
        }
        this.view.showEntity(product);
        this.view.show("prod add: ok");
    }
}