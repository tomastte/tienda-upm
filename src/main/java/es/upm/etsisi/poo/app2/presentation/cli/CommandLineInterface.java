package es.upm.etsisi.poo.app2.presentation.cli;

import es.upm.etsisi.poo.app2.presentation.cli.exceptions.CommandException;
import es.upm.etsisi.poo.app2.presentation.view.View;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineInterface {
    public static final String EXIT = "exit";
    //private static boolean ECHO_COMMANDS_MODE = false;
    private final Map<String, Command> commands;
    private final View view;

    public CommandLineInterface(View view) {
        this.view = view;
        this.commands = new LinkedHashMap<>();
    }

    public void add(Command command) {
        this.commands.put(command.name(), command);
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in);
        boolean exit;
        do {
            exit = this.runCommand(scanner);
        } while (!exit);
        return true;
    }

    /* ========= FILE MODE ========= */
    public boolean runCommandsFromFile(String fileName) throws IOException {
        //CommandLineInterface.ECHO_COMMANDS_MODE = true;
        Scanner fileScanner = new Scanner(Path.of(fileName));
        boolean exit;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;
            this.view.show("tUPM> " + line);
            Scanner lineScanner = new Scanner(line);
            exit = this.runCommand(lineScanner);
            if (exit)
                return true;
        }
        return true;
    }

    public boolean runCommand(Scanner scanner) {
        this.view.showCommandPrompt();
        String line = scanner.nextLine().trim();

        String command = this.commands.keySet().stream()
                .filter(cmd -> line.equals(cmd) || line.startsWith(cmd + " "))
                .findFirst()
                .orElseThrow(() -> new CommandException("Command '" + line + "' does not exist."));

        String paramsPart = line.substring(command.length()).trim();
        Scanner paramScanner = new Scanner(paramsPart);
        String[] params = this.scanParamsIfNeededAssured(paramScanner, command);

        if (EXIT.equals(command)) {
            return true;
        } else {
            this.commands.get(command).execute(params);
            this.view.show("");
        }
        return false;
    }

    private String[] scanParamsIfNeededAssured(Scanner scanner, String command) {
        List<String> expectedParams = commands.get(command).params();
        if (expectedParams.isEmpty()) {
            return new String[0];
        }
        String line;
        if (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();
        } else if (scanner.hasNext()) {
            line = scanner.next().trim();
        } else {
            return new String[0];
        }

        if (line.isEmpty()) {
            return new String[0];
        }
        List<String> params = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);

        while (m.find()) {
            String p;
            if (m.group(1) != null) {
                // Texto entre comillas
                p = m.group(1);
            } else {
                // Palabra normal
                p = m.group(2);
            }
            p = p.trim();
            p = p.replaceAll("[\\t\\n\\r]", "");
            p = p.replaceAll("\\s{2,}", " ");

            if (!p.isEmpty()) {
                params.add(p);
            }
        }
        return params.toArray(new String[0]);
    }

    public void help() {
        this.view.show("Commands:");
        for (Command command : this.commands.values()) {
            this.view.show("\t" + command.help());
        }
        this.view.show("");
        this.view.show("Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS");
        this.view.show("Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, ELECTRONICS 3%.");
    }
}