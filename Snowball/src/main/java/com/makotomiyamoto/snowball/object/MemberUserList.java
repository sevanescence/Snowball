package com.makotomiyamoto.snowball.object;

public class MemberUserList {

    private MemberUser[] memberUsers;

    public MemberUserList(MemberUser[] memberUsers) {
        this.memberUsers = memberUsers;
    }

    public MemberUser[] getMemberUsers() {
        return memberUsers;
    }

    public void setMemberUsers(MemberUser[] memberUsers) {
        this.memberUsers = memberUsers;
    }



}
