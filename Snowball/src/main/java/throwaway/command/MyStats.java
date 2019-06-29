package throwaway.command;

import com.makotomiyamoto.snowball.manager.MemberUserManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MyStats extends CommandExecutor {

    public MyStats(MessageReceivedEvent event, Permission permission) {
        super.event = event;
        super.permission = permission;
    }

    @Override
    @SuppressWarnings("all")
    public boolean cast(String label, String[] args) {

        if (!label.equalsIgnoreCase("myStats")) {
            return false;
        }

        if (!event.getMember().hasPermission(super.permission)) {
            return false;
        }

        if (!MemberUserManager.lookForUser("src/main/resources/userdata.json", super.event.getAuthor().getId())) {
            super.event.getChannel().sendMessage("You have no user data yet. Try sending a message!").complete();
            return false;
        }

        super.event.getChannel().sendMessage("stats will go here :3").complete();

        return true;

    }

}
