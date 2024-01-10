package org.perscholas.casestudy.formbean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.perscholas.casestudy.database.entity.gCharacter;
@Getter
@Setter
public class CreateMatchupFormBean {
    private Integer id;

    private String char1name;

    private String char2name;

    @NotEmpty(message = "Your Link is required")
    private String url;

    @Length(max = 2000, message = "Your description should be less than 2000 characters long. ")
    private String description;
}
