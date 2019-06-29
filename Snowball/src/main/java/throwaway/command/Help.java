package throwaway.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends CommandExecutor {

    Help(MessageReceivedEvent event) {
        super.event = event;
    }

    @Override
    @SuppressWarnings("all")
    public boolean cast(String label, String[] args) {

        if (!label.equalsIgnoreCase("help")) {
            return false;
        }

        String message = "Commands: => type \\\\> to escape the command key :p\n" +
                "> help - this page\n> uwu <arg-1> - ask Snowball to say uwu 3:\n" +
                "> mystats - check your user stats >:3";

        super.event.getChannel().sendMessage(message).complete();

        return true;

    }

}
