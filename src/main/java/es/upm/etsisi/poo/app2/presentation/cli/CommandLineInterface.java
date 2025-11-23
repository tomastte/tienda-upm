package es.upm.etsisi.poo.app2.presentation.cli;

import es.upm.etsisi.poo.app2.presentation.cli.exceptions.BadRequestException;
import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;

import static es.upm.etsisi.poo.app2.presentation.cli.Command.COMMAND_SEPARATOR;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

public class CommandLineInterface {
    public static final String EXIT = "exit";
    private static boolean ECHO_COMMANDS_MODE=false;
    private final Map<String, Command> commands;
    private final View view;

    public CommandLineInterface(View view) {
        this.view = view;
        this.commands = new HashMap<>();
    }

    public void add(Command command) {
        this.commands.put(command.name(), command);
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(COMMAND_SEPARATOR);
        boolean exit;
        do {
            exit = this.runCommand(scanner);
        } while (!exit);
        return true;
    }

    /* ========= FILE MODE ========= */
    public boolean runCommandsFromFile(String fileName) throws IOException {
        CommandLineInterface.ECHO_COMMANDS_MODE = true;
        Scanner scanner = new Scanner(Path.of(fileName)).useDelimiter(COMMAND_SEPARATOR);
        boolean exit;
        do {
            exit = this.runCommand(scanner);
        } while (!exit);
        return true;
    }

    public boolean runCommand(Scanner scanner) {
        this.view.showCommandPrompt();
        String command = scanner.next();
        if (!this.commands.containsKey(command)) {
            throw new CommandException("Comando '" + command + "' no exists.");
        }
        String[] params = this.scanParamsIfNeededAssured(scanner, command);
        if (EXIT.equals(command)) {
            return true;
        } else {
            this.commands.get(command).execute(params);
        }
        return false;
    }

    private String[] scanParamsIfNeededAssured(Scanner scanner, String command) {
        List<String> expectedParams = commands.get(command).params();
        if (expectedParams.isEmpty()) {
            return new String[0];
        }
        String[] foundParams = scanner.next().split(Command.PARAM_SEPARATOR);
        if (expectedParams.size() != foundParams.length) {
            throw new BadRequestException("Parámetros esperados: " + expectedParams +
                    ", encontrados " + Arrays.toString(foundParams));
        }
        return foundParams;
    }

    public void help() {
        for (Command command : this.commands.values()) {
            this.view.show(command.help());
        }
    }
}