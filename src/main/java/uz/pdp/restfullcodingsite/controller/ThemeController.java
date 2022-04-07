package uz.pdp.restfullcodingsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullcodingsite.entity.Language;
import uz.pdp.restfullcodingsite.entity.Theme;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.ThemeDto;
import uz.pdp.restfullcodingsite.service.ThemeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    /**
     * This class only make CRUD process on theme table
     */



    @Autowired
    ThemeService themeService;


    /**
     * Add Theme
     * @param themeDto
     * @return String message ,boolean success
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addThemeController(@Valid @RequestBody ThemeDto themeDto){
        return themeService.addTheme(themeDto);
    }


    /**
     * Get all themes
     * @return themes
     */
    @GetMapping
    public ResponseEntity<?> getThemesController(){
     return themeService.getThemes();
    }




    /**
     * Get Theme by id
     * @param id
     * @return exist ? Theme class : error message
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getThemeController(@PathVariable int id){
      return   themeService.getTheme(id);
    }


    /**
     * Update theme
      * @param id
     * @return String message,boolean success
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateThemeController(@PathVariable int id,@Valid @RequestBody ThemeDto themeDto){
        return themeService.updateTheme(id,themeDto);
    }


    /**
     * Delete Theme by id
     * @param id
     * @return String message, boolean success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteThemeController(@PathVariable int id){
        return themeService.deleteTheme(id);
    }



    /**
     * Handle exception
     *
     * @param ex
     * @return http message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }


}
