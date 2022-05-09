package com.example.aloalo.repo.iml;

import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;
import com.example.aloalo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepoIml implements UserRepo{
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Override
    public User findByUsername(String username) {
        User user = new User();

//        nếu có nhiều pram
//        StringBuilder sql = new StringBuilder();
//         sql.append("SELECT u FROM User u where u.username = :username");
//        Map<String, Object> params = new HashMap<>();
//        params.put("username", username);
//        Query query = entityManager.createQuery(sql.toString());
//        params.forEach(query::setParameter);
//        Object result = query.getSingleResult();

        String sql = "SELECT u FROM User u where u.username = :username";
        Query query = entityManager.createQuery(sql);
        query.setParameter("username", username);
        user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public void save(RegisterFormDto registerFormDto) {
        Connection connection;
        CallableStatement callableStatement;
        try {
            connection = dataSource.getConnection();
            callableStatement = connection.prepareCall("{call save(?,?,?)}");
            callableStatement.setString(1, registerFormDto.getUsername());
            callableStatement.setString(2, registerFormDto.getPassword());
            callableStatement.setString(3, registerFormDto.getName());
            callableStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
