package org.perscholas.casestudy.service;
import jakarta.persistence.FetchType;
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
import org.perscholas.casestudy.database.dao.CharacterDao;
@Slf4j
@Service
public class MatchupService {
    @Autowired
    private MatchupDao matchupDao;

    @Autowired
    private CharacterDao characterDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthUserService authUserService;

    public Matchup createMatchup(CreateMatchupFormBean form){
        log.debug("Id: " + form.getId());
        log.info("Character 1:" + form.getChar1name());
        log.info("Url: "+ form.getUrl());
        log.info("Character 2:" + form.getChar2name());
        log.info("Description: " + form.getDescription());
        User user = authUserService.loadCurrentUser();
        Matchup matchup = new Matchup();
        gCharacter c = characterDao.findByName(form.getChar1name());
        gCharacter c2 = characterDao.findByName(form.getChar2name());
        log.info("Character 1: " + c.getName());
        log.info("Character 2: " + c2.getName());
        matchup.setChar1(c);
        matchup.setChar2(c2);
        matchup.setUrl(form.getUrl());
        matchup.setDescription(form.getDescription());
        matchup.setUploadDate(new Date());
        matchup.setUser(user);
        return matchupDao.save(matchup);

    };
}