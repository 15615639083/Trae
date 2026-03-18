package com.zhifeizi.service;

import com.zhifeizi.entity.User;
import java.util.List;

public interface UserService {
    List<User> listUsers();
    User getUserById(Long id);
    boolean saveUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
}