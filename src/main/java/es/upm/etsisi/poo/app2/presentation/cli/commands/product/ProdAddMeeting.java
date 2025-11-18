package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.TimeProduct;
import es.upm.etsisi.poo.app2.data.model.shop.TimeProductType;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

import java.time.LocalDate;
import java.util.List;

public class ProdAddMeeting implements Command {

    private final ProductService productService;

    public ProdAddMeeting(ProductService productService) {
        this.productService = productService;
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
        return "Implements a new meeting product with optional id, name, price, expiration date and max people..";
    }

    @Override
    public void execute(List<String> params) {
        if (params.size() < 3 || params.size() > 4) {
            throw new CommandException("Usage: prod addFood [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        }
        String id = null;
        String name = "";
        int index = 0;
        if (!params.get(0).startsWith("\"")) {
            id = params.getFirst();
            index++;
        }
        if (!params.get(index).startsWith("\"")) {
            throw new CommandException("Usage: prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        }
        while (!params.get(index).endsWith("\"") && index < params.size()) {
            name += params.get(index) + " ";
            index++;
        }
        if (index >= params.size()) {
            throw new CommandException("Usage: prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        }
        name = name.trim();
        name = name.substring(1, name.length() - 2);
        if (params.size() - index != 3) {
            throw new CommandException("Usage: prod addMeeting [<id>] \"<name>\" <price> <expiration:yyyy-MM-dd> <max_people>");
        }
        double price = Double.parseDouble(params.get(index));
        index++;
        LocalDate expiration = LocalDate.parse(params.get(index));
        index++;
        int maxPeople = Integer.parseInt(params.get(index));
        TimeProduct product = new TimeProduct(name, TimeProductType.MEETING, price, expiration, maxPeople);
        if (id != null) {
            this.productService.add(product, id);
        } else {
            this.productService.add(product);
        }
        View.showEntity(product);
        View.show("prod addMeeting: ok");
    }
}