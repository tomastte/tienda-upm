package es.upm.etsisi.poo.app2.presentation.cli;

import es.upm.etsisi.poo.app2.presentation.view.View;

public class ErrorHandler {
    public void handlesErrors(CommandLineInterface commandLineInterface,
                              View view, String[] args) {

        view.showInit();
        try {
            if (args.length == 0) {
                commandLineInterface.runCommands();
            } else {
                commandLineInterface.runCommandsFromFile(args[0]);
            }
        } catch (Exception e) {
            view.showError("ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
        }
        view.showClose();
    }
}