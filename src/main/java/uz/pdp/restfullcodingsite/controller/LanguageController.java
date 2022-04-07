package uz.pdp.restfullcodingsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullcodingsite.entity.Language;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.LanguageDto;
import uz.pdp.restfullcodingsite.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/language")
public class LanguageController {

    /**
     * This class only make CRUD process on language table
     */




    @Autowired
    LanguageService languageService;


    /**
     * Add language
     * @param languageDto
     * @return String message,boolean success
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(LanguageDto languageDto)
    {
        return languageService.addLanguage(languageDto);
    }


    /**
     * Get languages
     * @return Languages
     */
    @GetMapping
    public ResponseEntity<?> getLanguages(@Valid  @RequestParam int page)
    {
        return languageService.getLanguages(page);
    }


    /**
     * Get language by id
     * @return Language
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> getLanguageByName(@PathVariable String name){
        return languageService.getLanguageByName(name);
    }


    /**
     * Update Language
     * @param languageDto
     * @param id
     * @return String message , boolean success
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLanguage(@RequestBody LanguageDto languageDto,@Valid @PathVariable int id){
       return languageService.updateLanguage(languageDto,id);
    }


    /**
     * Delete Language
     * @param id
     * @return String message ,boolean success
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteLanguage(@PathVariable int id){
       return languageService.deleteLanguage(id);
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
