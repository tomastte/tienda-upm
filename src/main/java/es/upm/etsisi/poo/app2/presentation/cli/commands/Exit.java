package es.upm.etsisi.poo.app2.presentation.cli.commands;

import es.upm.etsisi.poo.app2.presentation.cli.Command;

import java.util.List;

public class Exit implements Command {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Closes the application.";
    }

    @Override
    public void execute(List<String> params) {
    }
}