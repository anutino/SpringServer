package com.springserver.repository;

        import com.springserver.table.UserData;

public interface UserRepository {

    boolean createUser(UserData user);

    boolean deleteUser(String userId);

    boolean signInUser(String login, String password);
}
