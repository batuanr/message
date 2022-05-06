package com.example.aloalo.repo.iml;

import com.example.aloalo.dto.RegisterFormDto;
import com.example.aloalo.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepoIml implements UserRepo{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user = new User();
        StringBuilder sql = new StringBuilder();
         sql.append("SELECT u FROM User u where u.username = :username");
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        Query query = entityManager.createQuery(sql.toString());
        params.forEach(query::setParameter);
        Object result = query.getSingleResult();

        return user;
    }

    @Override
    public void save(RegisterFormDto registerFormDto) {

    }
}
