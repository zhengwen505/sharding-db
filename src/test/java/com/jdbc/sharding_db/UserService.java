package com.jdbc.sharding_db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public void save(List<User> users) throws Exception {
        try {
            userDao.addUserList(users);
        } catch (Exception e) {
            throw new MyException();
        }
    }
    // @Transactional(rollbackFor = Exception.class)
    // public void update(List<User> users) throws Exception {
    // try {
    // userDao.isExsit(user);
    // } catch (Exception e) {
    // throw new MyException();
    // }
    // }

}
