package org.perscholas.casestudy.database.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.perscholas.casestudy.database.entity.UserRole;

import java.util.List;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    public List<UserRole> findByUserId(Long userId);
}
