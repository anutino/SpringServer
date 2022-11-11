package com.springserver.repository;

import com.springserver.database.ExecuteQueries;
import com.springserver.database.Query;
import com.springserver.table.UserData;

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
