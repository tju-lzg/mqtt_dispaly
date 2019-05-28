package com.example.demo.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

}
