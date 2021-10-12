package com.rest.webservices.springwebservices.repository;

import com.rest.webservices.springwebservices.vo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserRepositoryStatic {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 5;

    static {
        users.add(new User(1, "Saurav", new Date()));
        users.add(new User(2, "Monika", new Date()));
        users.add(new User(3, "Rahul", new Date()));
        users.add(new User(4, "Khadu", new Date()));
        users.add(new User(5, "Kush", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(String id) {
        for(User user : users) {
            if(user.getId().toString().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User saveUser(User user) {
        if(user.getId()  == null) {
            user.setId(usersCount + 1);
        }
        usersCount++;
        users.add(user);
        return user;
    }

    public User deleteUser(String id) {
        Iterator<User> userIterator = users.listIterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if(user.getId().toString().equals(id)) {
                userIterator.remove();
                return user;
            }
        }
        return null;
    }

}
