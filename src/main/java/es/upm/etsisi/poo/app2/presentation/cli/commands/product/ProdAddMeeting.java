package es.upm.etsisi.poo.app2.presentation.cli.commands.product;

import es.upm.etsisi.poo.app2.data.model.shop.TimeProduct;
import es.upm.etsisi.poo.app2.data.model.shop.TimeProductType;
import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.shop.ProductService;

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
    public void execute(List<String> params) {
        String id = null;
        int index = 0;
        if (!params.getFirst().startsWith("\"")) {
            id = params.getFirst();
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
        Double price = Double.parseDouble(params.get(index));
        index++;
        LocalDate expiration = LocalDate.parse(params.get(index));
        index++;
        Integer maxPeople = Integer.parseInt(params.get(index));
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