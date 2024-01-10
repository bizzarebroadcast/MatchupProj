package org.perscholas.casestudy.database.dao;
import org.perscholas.casestudy.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    public User findByEmailIgnoreCase(String email);
}