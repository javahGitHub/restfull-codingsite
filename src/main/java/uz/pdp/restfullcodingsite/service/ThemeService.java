package uz.pdp.restfullcodingsite.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.restfullcodingsite.entity.Language;
import uz.pdp.restfullcodingsite.entity.Theme;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.ThemeDto;
import uz.pdp.restfullcodingsite.respository.LanguageRepo;
import uz.pdp.restfullcodingsite.respository.ThemeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {

    @Autowired
    ThemeRepo themeRepo;
    @Autowired
    LanguageRepo languageRepo;

    /**
     * Add Theme
     * @param themeDto
     * @return String message ,boolean success
     */
    public ResponseEntity<ApiResponse> addTheme(ThemeDto themeDto){

        //Check name of theme to be unique
        if(themeRepo.existsByName(themeDto.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("This Theme already exist",false));

        //Check language id
        Optional<Language> optionalLanguage = languageRepo.findById(themeDto.getLanguageId());
        if(!optionalLanguage.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Language not found",false));






        Theme theme=new Theme();
          theme.setLanguage(optionalLanguage.get());
          theme.setStars(theme.getStars());
          theme.setName(themeDto.getName());
          themeRepo.save(theme);

          return ResponseEntity.ok(new ApiResponse("Theme saved successfully",true));
    }


    /**
     * Get all themes
     * @return themes
     */
    public ResponseEntity<?> getThemes(){
        return ResponseEntity.ok(themeRepo.findAll());
    }


    /**
     * Get Theme by id
     * @param id
     * @return
     */
    public ResponseEntity<?> getTheme(int id){

        //Check theme id from repository
        Optional<Theme> optionalTheme = themeRepo.findById(id);
        if(!optionalTheme.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Theme not found",false));
        return ResponseEntity.ok(optionalTheme.get());
    }



    /**
     * Update theme
     * @param id
     * @return String message,boolean success
     */
    public ResponseEntity<ApiResponse> updateTheme(int id,ThemeDto themeDto){

        //Check theme id from repository
        Optional<Theme> optionalTheme = themeRepo.findById(id);
        if(!optionalTheme.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Theme not found by id",false));

        //Check name of theme to be unique
        if(themeRepo.existsByNameAndIdNot(themeDto.getName(),id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("This Theme name already exist",false));

        //Check language id
        Optional<Language> optionalLanguage = languageRepo.findById(themeDto.getLanguageId());
        if(!optionalLanguage.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Language not found",false));


        Theme theme=optionalTheme.get();
        theme.setLanguage(optionalLanguage.get());
        theme.setStars(themeDto.getStars());
        theme.setName(themeDto.getName());
        themeRepo.save(theme);
        return ResponseEntity.ok(new ApiResponse("Theme updated",true));


    }


    /**
     * Delete Theme by id
     * @param id
     * @return String message, boolean success
     */
    public ResponseEntity<ApiResponse> deleteTheme(@PathVariable int id){
        //Check theme id from repository
        if(themeRepo.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Theme not found by id",false));
        themeRepo.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Theme deleted successfully",true));

    }





}
