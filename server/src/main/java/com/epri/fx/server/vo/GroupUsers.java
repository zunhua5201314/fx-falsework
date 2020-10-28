package com.epri.fx.server.vo;


import com.epri.fx.server.entity.User;

import java.util.List;

/**
 *
 * @Description:
 *
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:56 上午
 */
public class GroupUsers {
    List<User> members;
    List<User> leaders;
    List<User> users;

    public GroupUsers() {
    }

    public GroupUsers(List<User> members, List<User> leaders, List<User> users) {
        this.members = members;
        this.leaders = leaders;
        this.users = users;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<User> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<User> leaders) {
        this.leaders = leaders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
