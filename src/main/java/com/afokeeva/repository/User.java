package com.afokeeva.repository;

import com.afokeeva.database.ExecuteQueries;
import com.afokeeva.database.Query;
import com.afokeeva.table.UserData;

public class User implements UserRepository {

    @Override
    public boolean createUser(UserData user) {
        return ExecuteQueries.isExecuteUpdate(new Query().getQueryInsertUser(user));
    }

    @Override
    public boolean deleteUser(String userId) {
        return ExecuteQueries.isExecuteUpdate(new Query().getQueryDeleteUser(userId));
    }

    @Override
    public boolean signInUser(String login, String password) {
        return ExecuteQueries.isExistsUser(new Query().getQuerySignInUser(login, password));
    }
}
