package com.example.android.memoryapp.model;

import com.example.android.memoryapp.model.Friend;
/**
 * Created by Anca on 18/03/2018.
 */

public class FriendBuilder {
    private Friend friend;

    public FriendBuilder(){
        friend = new Friend();
    }

    public FriendBuilder setId(int id){
        friend.setId(id);
        return this;
    }

    public FriendBuilder setFirstName(String firstName){
        friend.setFirstName(firstName);
        return this;
    }

    public FriendBuilder setLastName(String lastName){
        friend.setLastName(lastName);
        return this;
    }

    public FriendBuilder setHelpInfo(String helpInfo){
        friend.setHelpInfo(helpInfo);
        return this;
    }

    public FriendBuilder setImage(byte[] image){
        friend.setImage(image);
        return this;
    }

    public Friend build(){
        return this.friend;
    }

}
