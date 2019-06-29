package throwaway;

import com.makotomiyamoto.snowball.manager.StringManager;
import com.makotomiyamoto.snowball.object.MemberUser;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Deprecated
@SuppressWarnings("all")
public final class MemberMessage extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().isFromType(ChannelType.TEXT) ||
                !StringManager.tryForFilter(event.getMessage(), event) ||
                event.getMessage().toString().contains("_>") ||
                event.getAuthor().isBot()) {
            return;
        }

        String dir = "src/main/resources/userdata.json";

        if (UserDataManager.lookForUser(dir, event.getAuthor().getId())) {

            MemberUser memberUser = UserDataManager.getMemberUser(dir, event.getAuthor().getId());
            memberUser.setMessages(memberUser.getMessages() + 1);
            UserDataManager.writeMemberUser(dir, memberUser);

            if (memberUser.checkForLevelUp()) {
                UserDataManager.runLevelUp(dir, memberUser);
            }

            return;

        }

        MemberUser memberUser = new MemberUser(event.getAuthor().getId(), 1, 1);
        JsonFileManager.writeMemberUser(dir, memberUser);

    }

}