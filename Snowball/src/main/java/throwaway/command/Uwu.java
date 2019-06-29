package throwaway.command;

import com.makotomiyamoto.snowball.manager.CommandManager;
import com.makotomiyamoto.snowball.manager.FileManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Uwu extends CommandExecutor {

    public Uwu(MessageReceivedEvent event, Permission permission) {
        super.event = event;
        super.permission = permission;
    }

    @Override
    @SuppressWarnings("all")
    public boolean cast(String label, String[] args) {

        if (!label.equalsIgnoreCase("uwu")) {
            return false;
        }

        if (!event.getMember().hasPermission(super.permission)) {
            return false;
        }

        try {

            if (args.length > 1) {
                args = CommandManager.mergeArgsToLast(args, 1);
            }

            if (args[0].equalsIgnoreCase("pls")) {
                super.event.getChannel().sendMessage(FileManager.getKey("src/main/resources/config.txt", "uwuOkay")).complete();
                return true;
            }

            super.event.getChannel().sendMessage(FileManager.getKey("src/main/resources/config.txt", "uwuNope")).complete();

        } catch (ArrayIndexOutOfBoundsException e) {
            cast(label, new String[]{"empty_arg"});
        }

        return true;

    }

}
