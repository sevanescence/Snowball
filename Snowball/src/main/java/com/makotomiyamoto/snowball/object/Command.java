package com.makotomiyamoto.snowball.object;

public class Command {

    private String label;
    private String[] args;

    public Command(String label, String[] args) {
        this.label = label;
        this.args = args;
    }

    public String getLabel() {
        return label;
    }
    public String[] getArgs() {
        return args;
    }

}
