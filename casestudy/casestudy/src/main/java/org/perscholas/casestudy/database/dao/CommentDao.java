package org.perscholas.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.perscholas.casestudy.database.entity.Comment;
import org.perscholas.casestudy.database.entity.Matchup;
import java.util.List;
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findByMatchup(Matchup matchup);

}