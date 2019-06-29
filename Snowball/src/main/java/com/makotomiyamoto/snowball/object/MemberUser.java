package com.makotomiyamoto.snowball.object;

import net.dv8tion.jda.api.entities.User;

public class MemberUser {

    private String memberUuid;
    private int messages, level, warns, strikes, kicks;
    private String[] bans;

    public MemberUser(String memberUuid, int msgs, int lvl) {
        this.memberUuid = memberUuid;
        this.messages = msgs;
        this.level = lvl;
    }

    public MemberUser(String memberUuid, int msgs, int lvl, int warns, int strikes, int kicks, String[] bans) {
        this.memberUuid = memberUuid;
        this.messages = msgs;
        this.level = lvl;
        this.warns = warns;
        this.strikes = strikes;
        this.kicks = kicks;
        this.bans = bans;
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
    public int getWarns() {
        return warns;
    }
    public int getStrikes() {
        return strikes;
    }
    public void setWarns(int warns) {
        this.warns = warns;
    }
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }
    public int getKicks() {
        return kicks;
    }
    public void setKicks(int kicks) {
        this.kicks = kicks;
    }
    public String[] getBans() {
        return bans;
    }
    public void setBans(String[] bans) {
        this.bans = bans;
    }
    @SuppressWarnings("all")
    public String getBansToString() {
        if (bans.length < 1) {
            return "`no bans :3`";
        }
        String message = " ```";
        for (int i = 0; i < bans.length; i++) {
            message += " " + (i+1) + ". " + bans[i] + "\n";
        }
        message += "```";
        return message;
    }

}
