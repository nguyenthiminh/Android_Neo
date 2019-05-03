package com.nguyenminh.draggerexample.model.entiy;

import javax.inject.Inject;

public class Me {
    private Friend friend;

    @Inject
    public Me(Friend friend) {
        this.friend = friend;
    }

    public void quen(String name){
        friend.familiarly(name);
    }

    public void khongquen(){
        friend.unFamiliarly();
    }
    public String getRelationship(){
        return friend.getName();
    }
}
