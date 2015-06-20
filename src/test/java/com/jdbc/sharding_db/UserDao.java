package com.jdbc.sharding_db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addUser(User user) {
        String sql = "insert into user values(?,?)";
    }

    @Transactional
    public void addUserList(List<User> users) {
        String sql = "insert into user values(?,?)";
        int i = 0;
        for (User user : users) {
            if (i < 3) {
                jdbcTemplate.update(sql, user.getNumber(), user.getName());
                i++;
            } else {
                /**
                 * test transaction
                 */
                // throw new RuntimeException();
            }

        }
    }

    public boolean isExsit(User user) {
        String sql = "select * from user where id = ? ";
        User user1 = jdbcTemplate.queryForObject(sql, new Object[] { user.getNumber() }, User.class);
        ;
        if (user1 != null) {
            return true;
        }
        return false;
    }

    public void update(User user) {

        String sql = "update user set name =? where number = ? ";
        jdbcTemplate.update(sql, user.getName(), user.getNumber());

    }
}
