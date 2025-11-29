package es.upm.etsisi.poo.app2.presentation.cli;

import es.upm.etsisi.poo.app2.presentation.view.View;

import java.io.IOException;

public class ErrorHandler {
    public void handlesErrors(CommandLineInterface commandLineInterface,
                              View view, String[] args) {

        view.showInit();
        try {
            if (args.length == 0) {
                // MODO INTERACTIVO: bucle interno en runCommands()
                commandLineInterface.runCommands();
            } else {
                // MODO FICHERO: se ejecuta solo una vez
                commandLineInterface.runCommandsFromFile(args[0]);
            }
        } catch (Exception e) {
            view.showError("ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
        }
        view.showClose();
    }

}