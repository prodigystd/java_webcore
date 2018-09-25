package com.WebCore;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.WebCore.model.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Service
public class DB_interact {

    @Autowired
    DataSource dataSource;

    private ResultSet Select(String sql) {
        ResultSet rs = null;
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
     return rs;
    }

    public User get_user(String username)
    {
        User user = null;
        ResultSet rs = null;
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("Select * from users where name = ?");
            statement.setString(1,username);
            rs = statement.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public Role get_role(int role_id)
    {
        Role role = null;
        ResultSet rs = null;
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("Select * from roles where id = ?");
            statement.setLong(1,role_id);
            rs = statement.executeQuery();
            if(rs.next())
            {
                role = new Role();
                role.setId(role_id);
                role.setName(rs.getString("role"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return role;
    }

    public void Save_user(User user)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = dataSource.getConnection();
            statement = connection.
                    prepareStatement("INSERT INTO USERS (name,password,registered) VALUES(?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setDate(3, user.getRegistered_Date());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
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
