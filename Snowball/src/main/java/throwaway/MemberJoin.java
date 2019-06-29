package throwaway;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

@Deprecated
public class MemberJoin extends ListenerAdapter {

    @Override
    @SuppressWarnings("all")
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {

        //String dir = "src/main/resources/config.txt";

        List<TextChannel> channels = event.getJDA().getTextChannels();

        TextChannel channelInquiry = null;
        for (TextChannel channel : channels) {
            if (channel.getName().equalsIgnoreCase("rules")) {
                channelInquiry = channel;
                break;
            }
        }

        String welcome_message = "Welcome %user% to the Cats are Cute Discord! Be sure to read the " +
                channelInquiry.getAsMention() + " and enjoy your stay!";
        welcome_message = welcome_message.replaceAll("%user%", event.getMember().getAsMention());
        event.getGuild().getDefaultChannel().sendMessage(welcome_message).complete();

        String default_role = "Kitten";

        List<Role> roles = event.getJDA().getRoles();
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase(default_role)) {
                event.getGuild().addRoleToMember(event.getMember(), role).complete();
                break;
            }
        }

    }

}
