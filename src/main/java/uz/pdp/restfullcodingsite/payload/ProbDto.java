package uz.pdp.restfullcodingsite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.restfullcodingsite.entity.Theme;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProbDto {

    @NotNull(message = "Enter neither one word!")
    private String title;

    @NotNull(message = "Problem should has question!")
    private String question;

    private String solution;

    @NotNull(message = "Please enter specific code!")
    private String code;

    private boolean completed = false;

    @NotNull(message = "Problem should belong to theme id")
    private int themesId;


}
