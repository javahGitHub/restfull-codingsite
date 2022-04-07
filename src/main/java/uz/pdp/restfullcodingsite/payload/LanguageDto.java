package uz.pdp.restfullcodingsite.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class LanguageDto {

    @NotNull(message = "You should enter language name!!! ")
    private String name;

    private String helpSection;
}
