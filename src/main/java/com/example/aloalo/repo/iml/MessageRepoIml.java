package com.example.aloalo.repo.iml;

import com.example.aloalo.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepoIml implements MessageRepo {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DataSource dataSource;

    @Override
    public List<Message> findAll(Long senderId, Long receiverId) {
        String sql = "select * from message where ((sender_id = :senderId && receiver_id = :receiverId) || (sender_id = :receiverId && receiver_id = :senderId))";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("senderId", senderId);
        query.setParameter("receiverId", receiverId);
        List<Object[]> queryResult = query.getResultList();
        return queryResult.stream().map(o -> {
            Message message = new Message();
            message.setSenderId(Long.parseLong(o[3].toString()));
            message.setReceiverId(Long.parseLong(o[2].toString()));
            message.setId(Long.parseLong(o[0].toString()));
//            message.setReceiverId((Long) o[2]);
            message.setContent(o[1].toString());
            return message;
        }).collect(Collectors.toList());
    }

    @Override
    public void save(Message message) {
        Connection connection;
        CallableStatement callableStatement;
        try {
            connection = dataSource.getConnection();
            callableStatement = connection.prepareCall("{call saveM(?,?,?)}");
            callableStatement.setLong(1, message.getSenderId());
            callableStatement.setLong(2, message.getReceiverId());
            callableStatement.setString(3, message.getContent());
            callableStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
