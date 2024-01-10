package org.perscholas.casestudy.service;

import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.formbean.CreateMatchupFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.perscholas.casestudy.database.dao.MatchupDao;
import org.perscholas.casestudy.database.entity.Matchup;
import org.perscholas.casestudy.database.entity.User;
import org.perscholas.casestudy.database.dao.UserDao;
import java.util.Date;
import org.perscholas.casestudy.database.entity.gCharacter;
import org.perscholas.casestudy.database.dao.CommentDao;
import org.perscholas.casestudy.database.entity.Comment;
@Service
public class CommentService{
    @Autowired CommentDao commentDao;
    @Autowired MatchupDao matchupDao;
    @Autowired AuthUserService authUserService;
        public Comment createComment(String matchup, String commentText){
        User user = authUserService.loadCurrentUser();
        Comment comment = new Comment();
        Matchup m = matchupDao.findByDescription(matchup);
        comment.setUser(user);
        comment.setMatchup(m);
        comment.setComment(commentText);
        comment.setCreateDate(new Date());
        m.getComments().add(comment);
        matchupDao.save(m);
        // Save the comment to the database
        return commentDao.save(comment);
        }
}