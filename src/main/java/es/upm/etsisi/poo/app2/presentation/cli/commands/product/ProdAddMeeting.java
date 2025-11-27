package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.products.TimeProduct;
import es.upm.etsisi.poo.app2.data.model.shop.TimeProductType;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.ProductService;

import java.time.LocalDate;
import java.util.List;

public class ProdAddMeeting implements Command {

    private final ProductService productService;
    private final View view;

    public ProdAddMeeting(View view, ProductService productService) {
        this.productService = productService;
        this.view = view;
    }

    @Override
    public String name() {
        return "prod addMeeting";
    }

    @Override
    public List<String> params() {
        return List.of("[<id>]", "\"<name>\"", "<price>", "<expiration:yyyy-MM-dd>", "<max_people>");
    }

    @Override
    public String helpMessage() {
        return "Implements a new meeting product with optional id, name, price, expiration date and max people.";
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
        // Price
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        if (!params[index].matches("[+-]?\\d+(\\.\\d+)?([eE][+-]?\\d+)?")) {
            throw new CommandException("Usage: " + this.help());
        }
        String price = params[index];
        index++;
        // Expiration Date
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        if (!params[index].matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new CommandException("Usage: " + this.help());
        }
        String expiration = params[index];
        index++;
        // Max_people
        if (index >= params.length)
            throw new CommandException("Usage: " + this.help());
        if (!params[index].matches("-?\\d+"))
            throw new CommandException("Usage: " + this.help());
        String max_people = params[index];
        return new String[]{id, name.toString().trim(), price, expiration, max_people};
    }

    @Override
    public void execute(String[] params) {
        params = this.assessParams(params);
        String id = params[0];
        String name = params[1];
        Double price = Double.valueOf(params[2]);
        LocalDate expiration = LocalDate.parse(params[3]);
        Integer maxPeople = Integer.valueOf(params[4]);
        TimeProduct product = new TimeProduct(name, TimeProductType.MEETING, price, expiration, maxPeople);
        if (id != null) {
            this.productService.add(product, id);
        } else {
            this.productService.add(product);
        }
        this.view.showEntity(product);
        this.view.show("prod addMeeting: ok");
    }
}