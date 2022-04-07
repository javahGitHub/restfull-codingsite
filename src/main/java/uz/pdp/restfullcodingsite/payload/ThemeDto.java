package uz.pdp.restfullcodingsite.payload;

import lombok.Data;
import uz.pdp.restfullcodingsite.entity.Language;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class ThemeDto {
    @NotNull(message = "Name blank can't be empty!")
    private String name;

    private double stars;

    @NotNull(message = "Enter language id that theme is belong!")
    private int  languageId;
}
