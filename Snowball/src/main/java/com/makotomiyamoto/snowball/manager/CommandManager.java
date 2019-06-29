package com.makotomiyamoto.snowball.manager;

public class CommandManager {

    @Deprecated
    public static String[] mergeArgsToLast(String[] args, int index) {

        String[] newArgs = new String[index - 1];
        System.arraycopy(args, 0, newArgs, 0, index - 1);

        if (args.length > index) {
            for (String arg : args) {
                newArgs[index - 1] = newArgs[index - 1] + " " + arg;
            }
        }

        return newArgs;

    }

    public static String[] merge(String[] args, int argCount) {

        if (args.length < 1) {
            args = new String[argCount];
            for (int i = 0; i < args.length; i++) {
                args[i] = "empty_arg";
            }
            return args;
        }

        if (args.length <= argCount) {
            return args;
        }

        String[] newArgs = new String[argCount - 1];
        System.arraycopy(args, 0, newArgs, 0, argCount - 1);

        for (String arg : args) {
            newArgs[argCount - 1] = newArgs[argCount - 1] + " " + arg;
        }

        return newArgs;

    }

}
