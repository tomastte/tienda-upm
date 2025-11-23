package es.upm.etsisi.poo.app2.presentation.cli.commands;

import es.upm.etsisi.poo.app2.presentation.cli.Command;
import es.upm.etsisi.poo.app2.presentation.cli.CommandLineInterface;

import java.util.List;

public class Help implements Command {

    CommandLineInterface cli;

    public Help(CommandLineInterface cli) {
        this.cli = cli;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public List<String> params() {
        return List.of();
    }

    @Override
    public String helpMessage() {
        return "Shows the list of commands available.";
    }

    @Override
    public void execute(String[] params) {
        this.cli.help();
    }
}