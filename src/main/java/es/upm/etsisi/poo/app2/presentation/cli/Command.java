package es.upm.etsisi.poo.app2.presentation.cli;

import java.util.List;

public interface Command {
    public String name();

    public List<String> params();

    public String helpMessage();

    public void execute(List<String> params);

    default String help() {
        StringBuilder result = new StringBuilder(this.name());
        if (!this.params().isEmpty()) {
            result.append(" ").append(String.join(" ", this.params()));
        }
        return result.toString();
    }
}