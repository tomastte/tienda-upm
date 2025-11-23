package es.upm.etsisi.poo.app2.presentation.cli;

import es.upm.etsisi.poo.app2.presentation.view.View;

import java.io.IOException;

public class ErrorHandler {
    public void handlesErrors(CommandLineInterface commandLineInterface,
                              View view, String[] args) {

        view.showTitle("App Tienda UPM");
        boolean exit = false;
        while (!exit) {
            try {
                if (args.length == 0) {
                    exit = commandLineInterface.runCommands();
                } else {
                    exit = commandLineInterface.runCommandsFromFile(args[0]);
                }

            } catch (IOException ioException){
                exit=true;
                view.showError("ERROR (" + ioException.getClass().getSimpleName() + ") >>> " + ioException.getMessage());
            } catch (Exception e) {
                view.showError("ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
            }
        }
        view.showTitle("Hasta pronto!");
    }
}
