package com.WebCore;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.WebCore.model.*;

public class DB_interact {
    @Autowired
    DataSource dataSource;

    private ResultSet Select(String sql) {
        ResultSet rs = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.getErrorCode();
        }
     return rs;
    }

    public List<User> getUsers()
    {
        ResultSet rs  = Select("Select * from users");
        User user;
        List<User> users = new ArrayList<>();
        try {
            while(rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }catch (SQLException e) {
            e.getErrorCode();
        }
        return users;

    }

}
