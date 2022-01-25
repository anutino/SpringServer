package com.afokeeva.repository;

        import com.afokeeva.table.UserData;

public interface UserRepository {

    boolean createUser(UserData user);

    boolean deleteUser(String userId);

    boolean signInUser(String login, String password);
}
