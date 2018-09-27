package com.WebCore.service;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
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
        String sql = "Select u.*,ur.role_id,r.role from users u \n" +
                "left join user_roles ur on u.id = ur.user_id\n" +
                "left join roles r on ur.role_id = r.id where u.name = ? ";
        HashSet<Role> roles = new HashSet<Role>();
        Role role = null;
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            rs = statement.executeQuery();
            if(rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                role = new Role();
                role.setName(rs.getString("role"));
                role.setId(rs.getInt("role_id"));
                roles.add(role);
                while (rs.next())
                {
                    role = new Role();
                    role.setName(rs.getString("role"));
                    role.setId(rs.getInt("role_id"));
                    roles.add(role);
                }
                user.setRoles(roles);
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
            ResultSet rs = connection.
                    prepareStatement("SELECT LAST_INSERT_ID();").
                    executeQuery();
            if(rs.next()) {
                user.setId(rs.getInt(1));
            }
            else
            {
                connection.rollback();
            }
            statement.close();
            connection.close();
            assign_Role(user);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void assign_Role(User user)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = dataSource.getConnection();
            statement = connection.
                    prepareStatement("INSERT INTO USER_ROLES (user_id,role_id) VALUES(?,?)");
            for (Role role : user.getRoles()){
                statement.setLong(1, user.getId());
                statement.setInt(2, role.getId());
                statement.addBatch();
            }
            statement.executeBatch();
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
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        }catch (SQLException e) {
            e.getErrorCode();
        }
        return users;
    }

    public void save_article(Article article)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = dataSource.getConnection();
            statement = connection.
                    prepareStatement("INSERT INTO Articles (user_id,article_name,article_text) VALUES(?,?,?)");
            statement.setInt(1, article.getUser_id());
            statement.setString(2, article.getArticleHeader());
            statement.setString(3, article.getArticleContent());
            statement.executeUpdate();
            /*
            ResultSet rs = connection.
                    prepareStatement("SELECT LAST_INSERT_ID();").
                    executeQuery();
            if(rs.next()) {
                article.setArticleId(rs.getLong(1));
            }
            else
            {
                connection.rollback();
            }*/
            statement.close();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public List<Article> getUserArticles(User user)
    {
        List<Article> articles = null;
        Article article = null;
        ResultSet rs = null;
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("Select * from Articles where user_id = ?");
            statement.setInt(1,user.getId());
            rs = statement.executeQuery();
            articles = new ArrayList<>();
            while (rs.next())
            {
                article = new Article();
                article.setArticleId(rs.getLong("article_id"));
                article.setArticleHeader(rs.getString("article_name"));
                article.setArticleContent(rs.getString("article_text"));
                article.setUser_id(rs.getInt("user_id"));
                articles.add(article);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return articles;
    }

    public List<Article> getAllArticles()
    {
        ResultSet rs  = Select("Select * from Articles");
        List<Article> articles = new ArrayList<>();
        Article article = null;
        try {
            while(rs.next()) {
                article = new Article();
                article.setArticleId(rs.getLong("article_id"));
                article.setArticleHeader(rs.getString("article_name"));
                article.setArticleContent(rs.getString("article_text"));
                article.setUser_id(rs.getInt("user_id"));
                articles.add(article);
            }
        }catch (SQLException e) {
            e.getErrorCode();
        }
        return articles;
    }
}
