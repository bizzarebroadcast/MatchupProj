package org.perscholas.casestudy.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.formbean.RegisterUserFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.perscholas.casestudy.database.entity.User;
import org.perscholas.casestudy.database.dao.UserDao;
import java.util.Date;
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User createNewUser(RegisterUserFormBean form) {
        User user = new User();

        user.setEmail(form.getEmail().toLowerCase());

        String encoded = passwordEncoder.encode(form.getPassword());
        log.debug("Encoded password: " + encoded);
        user.setPassword(encoded);

        // this will create a date in the database with the current time (right now)
        user.setCreateDate(new Date());

        return userDao.save(user);
    }

    // Additional methods for managing user-related functionality
}