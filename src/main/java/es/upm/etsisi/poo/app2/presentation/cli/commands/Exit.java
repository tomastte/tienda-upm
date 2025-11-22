package es.upm.etsisi.poo.app2.presentation.cli.commands;

import es.upm.etsisi.poo.app2.presentation.cli.Command;

import java.util.List;

import static es.upm.etsisi.poo.app2.presentation.cli.CommandLineInterface.EXIT;

public class Exit implements Command {

    @Override
    public String name() {
        return EXIT;
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
    public void execute(String[] params) {
    }
}