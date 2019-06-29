package throwaway.command;

import com.makotomiyamoto.snowball.object.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public abstract class CommandExecutor {

    @SuppressWarnings("all")
    public enum CommandId {
        INVALID_COMMAND,
        HELP,
        UWU,
        MYSTATS
    }

    public static CommandExecutor findCommand(Command cmd, MessageReceivedEvent event) {
        try {
            CommandId commandId = CommandId.valueOf(cmd.getLabel().toUpperCase());
            switch (commandId) {
                case UWU:
                    return new Uwu(event, Permission.MESSAGE_WRITE);
                case MYSTATS:
                    return new MyStats(event, Permission.MESSAGE_WRITE);
                case HELP:
                    return new Help(event);
                default:
                    return null;
            }
        } catch (IllegalArgumentException e) {
            return new Invalid_Command();
        }
    }

    public MessageReceivedEvent event;
    public Permission permission;

    public CommandExecutor() {
    }

    public CommandExecutor(MessageReceivedEvent event, Permission permission) {
        this.event = event;
        this.permission = permission;
    }

    public void cast(Command cmd) {
        cast(cmd.getLabel(), cmd.getArgs());
    }

    public abstract boolean cast(String label, String[] args);

}
