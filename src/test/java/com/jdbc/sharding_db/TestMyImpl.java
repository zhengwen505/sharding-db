package com.jdbc.sharding_db;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jdbc.route.ContextHoder;
import com.jdbc.route.RouteParameter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class TestMyImpl {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    public static Logger logger = LoggerFactory.getLogger(TestMyImpl.class);

    @Test
    public void test() throws Exception {
        List<User> users = new ArrayList<User>();

        RouteParameter parameter = new RouteParameter(1);
        ContextHoder.setRouteParameters(parameter);
        User user = new User(1, "zw");
         for (int i = 0; i < 100; i++) {
         User u = new User(i, "zw");
         users.add(u);
         }
         userService.save(users);
         // RouteParameter parameter = new RouteParameter(1);
         ContextHoder.setRouteParameters(parameter);
         userDao.addUser(user);
         RouteParameter parameter1 = new RouteParameter(2);
         ContextHoder.setRouteParameters(parameter1);
         userDao.addUser(user);

    }

}
