package ua.kiev.prog;

import java.util.HashMap;

public class UserList {
    private static final HashMap<String, String> users = new HashMap<>();

    static  {
        users.put("user1", "1111");
        users.put("user2", "2222");
        users.put("user3", "3333");
        users.put("user4", "4444");
    }

    public static HashMap<String, String> getInstance() {
        return users;
    }

    public synchronized void add(User user) {
        users.put(user.getLogin(), user.getPassword());
    }
}
