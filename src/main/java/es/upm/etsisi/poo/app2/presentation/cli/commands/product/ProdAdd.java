package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.Category;
import es.upm.etsisi.poo.app2.data.model.shop.products.BasicProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.CustomProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.Product;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.BadRequestException;
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
        // Formato esperado (según params()):
        // [ [<id>], "<name>", "<category>", "<price>", [<numberTexts>] ]

        if (params == null || params.length < 3 || params.length > 5) {
            throw new BadRequestException("Usage: " + this.help());
        }

        int index = 0;

        // ---- Id opcional ----
        String id = null;
        // Si hay al menos 4 parámetros y el primero es un entero, lo tomamos como id
        if (params.length >= 4 && params[0].matches("-?\\d+")) {
            id = params[0].trim();
            index++;
        }

        // ---- Name (obligatorio) ----
        if (index >= params.length) {
            throw new BadRequestException("Usage: " + this.help());
        }
        String name = params[index].trim();
        if (name.isEmpty()) {
            throw new BadRequestException("Usage: " + this.help());
        }
        index++;

        // ---- Category (obligatorio) ----
        if (index >= params.length) {
            throw new BadRequestException("Usage: " + this.help());
        }
        String categoryStr = params[index].trim();
        try {
            // valida que sea una categoría correcta
            Category.valueOf(categoryStr);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Usage: " + this.help());
        }
        index++;

        // ---- Price (obligatorio) ----
        if (index >= params.length) {
            throw new BadRequestException("Usage: " + this.help());
        }
        String priceStr = params[index].trim();
        try {
            Double.parseDouble(priceStr); // validación de número
        } catch (NumberFormatException ex) {
            throw new BadRequestException("Usage: " + this.help());
        }
        index++;

        // ---- numberTexts (opcional) ----
        String numberTexts = null;
        if (index < params.length) {
            String nt = params[index].trim();
            if (!nt.isEmpty()) {
                try {
                    Integer.parseInt(nt); // validación de entero
                } catch (NumberFormatException ex) {
                    throw new BadRequestException("Usage: " + this.help());
                }
                numberTexts = nt;
                index++;
            }
        }

        // Si aún quedan parámetros, sobran → error de uso
        if (index != params.length) {
            throw new BadRequestException("Usage: " + this.help());
        }

        // Normalizado: [id, name, category, price, numberTexts]
        return new String[]{id, name, categoryStr, priceStr, numberTexts};
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