package org.perscholas.casestudy.controller;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.casestudy.database.dao.CharacterDao;
import org.perscholas.casestudy.database.dao.MatchupDao;
import org.perscholas.casestudy.formbean.CreateMatchupFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.perscholas.casestudy.service.MatchupService;
import org.springframework.web.servlet.ModelAndView;
import org.perscholas.casestudy.database.entity.Matchup;
import java.util.List;
import org.perscholas.casestudy.database.entity.Comment;
import org.perscholas.casestudy.database.entity.gCharacter;
import org.perscholas.casestudy.service.CommentService;
import org.perscholas.casestudy.service.AuthUserService;
@Slf4j
@Controller
public class MatchupController {

    @Autowired
    private MatchupService matchupService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MatchupDao matchupDao;
    @Autowired
    private CharacterDao characterDao;

    @Autowired
    private AuthUserService authenticatedUserService;
    @GetMapping("/matchup/addComment")
    public ModelAndView addComment(@RequestParam String matchup, @RequestParam String commentText) {

        // Create the comment
        Comment comment = commentService.createComment(matchup, commentText);
        // Redirect back to the matchup display page
        ModelAndView modelAndView = new ModelAndView("redirect:/matchup/display");
        modelAndView.addObject("success", "Comment added successfully");

        return modelAndView;
    }
    @GetMapping("/matchup/display")
    public ModelAndView displayMatchups(@RequestParam(required = false) String filterChar1,
                                        @RequestParam(required = false) String filterChar2) {
        ModelAndView modelAndView = new ModelAndView("matchup/display");
        gCharacter char1 = characterDao.findByName(filterChar1);
        gCharacter char2 = characterDao.findByName(filterChar2);
        if (!StringUtils.isEmpty(filterChar1) && !StringUtils.isEmpty(filterChar2)){
            List<Matchup> matchups = matchupDao.findByCharacters(char1, char2);
            modelAndView.addObject("matchups", matchups);
        }else if((!StringUtils.isEmpty(filterChar1) && StringUtils.isEmpty(filterChar2))){
            List<Matchup> matchups = matchupDao.findByCharacter(char1);
            modelAndView.addObject("matchups", matchups);
        }else if((StringUtils.isEmpty(filterChar1) && !StringUtils.isEmpty(filterChar2))){
            List<Matchup> matchups = matchupDao.findByCharacter(char2);
            modelAndView.addObject("matchups", matchups);
        }else{
            List<Matchup> matchups = matchupDao.getAllMatchups();
            modelAndView.addObject("matchups", matchups);
        }
        // Retrieve all matchups from the database
        // Retrieve all characters for dropdown menus
        List<gCharacter> characters = characterDao.findAllCharacters();
        if (characters.isEmpty()) {
            log.info("its empty");
        }for(gCharacter g: characters){
            log.info(g.getName());
        }

        modelAndView.addObject("characters", characters);

        return modelAndView;
    }
    @GetMapping("/matchup/create")
    public ModelAndView createMatchup() {
        ModelAndView response = new ModelAndView("matchup/create");
        List<gCharacter> characters = characterDao.findAllCharacters();
        response.addObject("characters", characters);
        return response;
    }

    @GetMapping("/matchup/createSubmit")
    public ModelAndView createMatchupSubmit(@Valid CreateMatchupFormBean form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("######################### In create matchup submit - has errors #########################");
            ModelAndView response = new ModelAndView("customer/create");

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        log.info("######################### In create matchup submit - no error found #########################");

        Matchup m = matchupService.createMatchup(form);

        // the view name can either be a jsp file name or a redirect to another controller method
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/matchup/display?success=Matchup Saved Successfully");

        return response;


    }

    // Additional methods for handling matchups, such as viewing, editing, and deleting

    // Helper method to convert the form bean to a DTO or entity if needed
}