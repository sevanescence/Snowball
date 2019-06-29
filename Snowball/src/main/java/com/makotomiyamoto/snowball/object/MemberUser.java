package com.makotomiyamoto.snowball.object;

import net.dv8tion.jda.api.entities.User;

public class MemberUser {

    private String memberUuid;
    private int messages, level;

    public MemberUser(String memberUuid, int msgs, int lvl) {
        this.memberUuid = memberUuid;
        this.messages = msgs;
        this.level = lvl;
    }

    public MemberUser(User user) {
        this.memberUuid = user.getId();
        this.messages = 0;
        this.level = 0;
    }

    public String getMemberUuid() {
        return memberUuid;
    }
    public int getMessages() {
        return messages;
    }
    public int getLevel() {
        return level;
    }
    public void setMemberUuid(String id) {
        this.memberUuid = id;
    }
    public void setMessages(int msgs) {
        this.messages = msgs;
    }
    public void setLevel(int lvl) {
        this.level = lvl;
    }
    public boolean checkForLevelUp() {
        return this.messages >= Math.round(100 * (Math.pow(level, 1.5)));
    }
    public void tryLevelUp() {
        if (!checkForLevelUp()) return;
        this.level++;
        switch (level) {
            case 5:
                System.out.println("User has reached level 5! :D");
                break;
            case 10:
                System.out.println("User has reached 10! >:D");
                break;
            default:
                System.out.println("User is level " + this.level);
                break;
        }
    }

}
