package es.upm.etsisi.poo.app2.presentation.cli.commands;

import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.view.View;

import java.util.List;

public class Echo implements Command {

    private final View view;

    public Echo(View view) {
        this.view = view;
    }

    @Override
    public String name() {
        return "echo";
    }

    @Override
    public List<String> params() {
        return List.of("\"<text>\"");
    }

    @Override
    public String helpMessage() {
        return "Prints the provided text.";
    }

    @Override
    public void execute(List<String> params) {
        if (params.isEmpty()) {
            this.view.show("\"\"");
        } else {
            String text = String.join(" ", params);
            this.view.show("\"" + text + "\"");
        }
    }
}